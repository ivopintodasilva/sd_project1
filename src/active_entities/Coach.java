package active_entities;

import enums.CoachState;
import interfaces.IPlaygroundCoach;
import interfaces.IContestantsBenchCoach;
import interfaces.IRefereeSiteCoach;
import interfaces.IRepoCoach;

public class Coach extends Thread {

    //IDENTIFIERS
    private int id;
    private int team_id;
    private IContestantsBenchCoach contestants_bench;
    private IRefereeSiteCoach referee_site;
    private IPlaygroundCoach playground;
    private IRepoCoach repo;

    public Coach(int id, int team_id, IPlaygroundCoach playground, IRefereeSiteCoach referee_site,
                 IContestantsBenchCoach contestants_bench, IRepoCoach repo) {
        this.id = id;
        this.team_id = team_id;
        this.playground = playground;
        this.referee_site = referee_site;
        this.contestants_bench = contestants_bench;
        this.repo = repo;
    }

    public void run() {

        CoachState state = CoachState.WAIT_FOR_REFEREE_COMMAND;
        repo.coachLog(this.team_id, state);


        while (true){
            switch (state){
                case WAIT_FOR_REFEREE_COMMAND:
                    this.contestants_bench.callContestants();
                    state = CoachState.ASSEMBLE_TEAM;
                    repo.coachLog(this.team_id, state);
                    break;
                case ASSEMBLE_TEAM:
                    this.contestants_bench.informReferee();
                    state = CoachState.WATCH_TRIAL;
                    repo.coachLog(this.team_id, state);
                    break;
                case WATCH_TRIAL:
                    this.contestants_bench.reviewNotes();
                    state = CoachState.WAIT_FOR_REFEREE_COMMAND;
                    repo.coachLog(this.team_id, state);
                    break;
                default:
                    state= CoachState.WAIT_FOR_REFEREE_COMMAND;
                    repo.coachLog(this.team_id, state);
                    break;
            }
        }

        //System.out.println("Coach " + this.id + " finished execution");
    }

    public int getCoachId() {
        return id;
    }

    public int getTeam_id() {
        return team_id;
    }

}
