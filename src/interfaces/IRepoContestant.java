package interfaces;

import enums.ContestantState;

/**
 * Created by tiago on 21-03-2016.
 */
public interface IRepoContestant {
    void contestantLog(int id, int team_id, ContestantState state);
}
