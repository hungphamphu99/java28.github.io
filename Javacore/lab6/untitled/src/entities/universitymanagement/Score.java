package entities.universitymanagement;

public class Score {
    private double midScore;
    private double finalScore;
    private double overallScore;

    public Score(double midScore, double finalScore, double overallScore) {
        this.midScore = midScore;
        this.finalScore = finalScore;
        this.overallScore = overallScore;
    }

    public double getMidScore() {
        return midScore;
    }

    public double getFinalScore() {
        return finalScore;
    }

    public double getOverallScore() {
        return overallScore;
    }

    @Override
    public String toString() {
        return "Score{" +
                "midScore=" + midScore +
                ", finalScore=" + finalScore +
                ", overallScore=" + overallScore +
                '}';
    }
}
