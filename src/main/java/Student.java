import java.io.Serializable;
import java.util.Map;


public record Student(String name, int englishScore, int mathScore) implements Named {
    int sum(){
        return this.englishScore() + this.mathScore();
    }

    int average(){
        return (this.englishScore() + this.mathScore() / 2);
    }

    int maxScore() {
        return Math.max(this.englishScore(), this.mathScore());
    }

    Map<String, ? extends Serializable> summary(){
        return Map.of(
                "name", this.name(),
                "englishScore", this.englishScore(),
                "mathScore", this.mathScore(),
                "totalScore", this.sum(),
                "averageScore", this.average(),
                "maxScore", this.mathScore());
    }
}
