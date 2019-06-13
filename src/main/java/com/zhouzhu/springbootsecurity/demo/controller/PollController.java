package com.zhouzhu.springbootsecurity.demo.controller;

import com.zhouzhu.springbootsecurity.demo.payload.*;
import com.zhouzhu.springbootsecurity.demo.pojo.Poll;
import com.zhouzhu.springbootsecurity.demo.repository.PollRepository;
import com.zhouzhu.springbootsecurity.demo.repository.UserRepository;
import com.zhouzhu.springbootsecurity.demo.repository.VoteRepository;
import com.zhouzhu.springbootsecurity.demo.security.CurrentUser;
import com.zhouzhu.springbootsecurity.demo.security.UserPrincipal;
import com.zhouzhu.springbootsecurity.demo.service.PollService;
import com.zhouzhu.springbootsecurity.demo.util.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

/**
 * @author zhouzhu
 * @Description
 * @create 2019-06-13 15:58
 */
@RestController
@RequestMapping("/api/polls")
public class PollController {
    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PollService pollService;

    private static final Logger logger= LoggerFactory.getLogger(PollController.class);

    @GetMapping
    public PagedResponse<PollResponse> getPolls(@CurrentUser UserPrincipal currentUser,
                                                @RequestParam(value = "page",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                @RequestParam(value = "size",defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size){
        return pollService.getAllPolls(currentUser,page,size);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createPoll(@Valid @RequestBody PollRequest pollRequest){
        Poll poll=pollService.createPoll(pollRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{pollId}")
                .buildAndExpand(poll.getId()).toUri();
        return ResponseEntity.created(location).body(new ApiResponse(true,"Poll Created Successfully"));
    }
    @GetMapping("/{pollId}")
    public PollResponse getPollById(@CurrentUser UserPrincipal currentUser,
                                    @PathVariable Long pollId){
        return pollService.getPollById(pollId,currentUser);
    }
    @PostMapping("/{pollId}/votes")
    @PreAuthorize("hasRole('USER')")
    public PollResponse castVote(@CurrentUser UserPrincipal currentUser,
                                 @PathVariable Long pollId,
                                 @Valid @RequestBody VoteRequest voteRequest){
        return pollService.castVoteAndGetUpdatedPoll(pollId,voteRequest,currentUser);
    }
}
