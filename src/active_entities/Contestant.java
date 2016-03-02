package active_entities;


import shared_mem.ContestantsBench;
import shared_mem.Playground;
import shared_mem.RefereeSite;

public class Contestant extends Thread {
    //IDENTIFIERS
    private int id;
    private int team_id;
    private int strength;
    private ContestantsBench contestants_bench;
    private RefereeSite referee_site;
    private Playground playground;
    //STATES
    private boolean SEAT_AT_THE_BENCH;
    private boolean STAND_IN_POSITION;
    private boolean DO_YOUR_BEST;

    public Contestant(int id, int team_id, int strength,
                      Playground playground, RefereeSite referee_site, ContestantsBench contestants_bench){
        this.id = id;
        this.team_id = team_id;
        this.strength = strength;
        this.playground = playground;
        this.referee_site = referee_site;
        this.contestants_bench = contestants_bench;
    }

    public void run() {
        contestants_bench.seatDown();
        this.SEAT_AT_THE_BENCH = true;

        playground.followCoachAdvice();
        this.SEAT_AT_THE_BENCH = false;
        this.STAND_IN_POSITION = true;

        playground.getReady();
        this.STAND_IN_POSITION = false;
        this.DO_YOUR_BEST = true;

        System.out.println("Contestant " + this.id + " finished execution");

    }

    public int getContestantId() {
        return id;
    }

    public int getTeam_id() {
        return team_id;
    }

    public int getStrength() {
        return strength;
    }
}
