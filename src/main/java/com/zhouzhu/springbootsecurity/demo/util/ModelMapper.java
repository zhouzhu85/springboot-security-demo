package com.zhouzhu.springbootsecurity.demo.util;

import com.zhouzhu.springbootsecurity.demo.payload.ChoiceResponse;
import com.zhouzhu.springbootsecurity.demo.payload.PollResponse;
import com.zhouzhu.springbootsecurity.demo.payload.UserSummary;
import com.zhouzhu.springbootsecurity.demo.pojo.Poll;
import com.zhouzhu.springbootsecurity.demo.pojo.User;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zhouzhu
 * @Description
 * @create 2019-06-13 15:43
 */
public class ModelMapper {
    public static PollResponse mapPollToPollResponse(Poll poll, Map<Long,Long> choiceVotesMap, User creator,Long userVote){
        PollResponse pollResponse=new PollResponse();
        pollResponse.setId(poll.getId());
        pollResponse.setQuestion(poll.getQuestion());
        pollResponse.setCreationDateTime(poll.getCreatedAt());
        pollResponse.setExpirationDateTime(poll.getExpirationDateTime());
        Instant now=Instant.now();
        pollResponse.setExpired(poll.getExpirationDateTime().isBefore(now));

        List<ChoiceResponse> choiceResponses = poll.getChoices().stream().map(choice -> {
            ChoiceResponse choiceResponse = new ChoiceResponse();
            choiceResponse.setId(choice.getId());
            choiceResponse.setText(choice.getText());
            if (choiceVotesMap.containsKey(choice.getId())) {
                choiceResponse.setVoteCount(choiceVotesMap.get(choice.getId()));
            } else {
                choiceResponse.setVoteCount(0);
            }
            return choiceResponse;
        }).collect(Collectors.toList());
        pollResponse.setChoices(choiceResponses);
        UserSummary creatorSummary = new UserSummary(creator.getId(), creator.getUsername(), creator.getName());
        pollResponse.setCreatedBy(creatorSummary);
        if (userVote != null){
            pollResponse.setSelectedChoice(userVote);
        }
        long totalVotes = pollResponse.getChoices().stream().mapToLong(ChoiceResponse::getVoteCount).sum();
        pollResponse.setTotalVotes(totalVotes);
        return pollResponse;
    }
}
