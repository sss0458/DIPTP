package org.example.domain.vo;

public class StudentWorkStatisticVo {
    private Long totalCount;
    private Long submitCount;
    private Long reviewedCount;
    private Double avgScore;
    private Double maxScore;
    private Double minScore;

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Long getSubmitCount() {
        return submitCount;
    }

    public void setSubmitCount(Long submitCount) {
        this.submitCount = submitCount;
    }

    public Long getReviewedCount() {
        return reviewedCount;
    }

    public void setReviewedCount(Long reviewedCount) {
        this.reviewedCount = reviewedCount;
    }

    public Double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(Double avgScore) {
        this.avgScore = avgScore;
    }

    public Double getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Double maxScore) {
        this.maxScore = maxScore;
    }

    public Double getMinScore() {
        return minScore;
    }

    public void setMinScore(Double minScore) {
        this.minScore = minScore;
    }

    @Override
    public String toString() {
        return "StudentWorkStatisticVo{" +
                "totalCount=" + totalCount +
                ", submitCount=" + submitCount +
                ", reviewedCount=" + reviewedCount +
                ", avgScore=" + avgScore +
                ", maxScore=" + maxScore +
                ", minScore=" + minScore +
                '}';
    }
}