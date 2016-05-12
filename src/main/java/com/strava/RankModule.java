package com.strava;

import com.strava.models.Segment;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RankModule {

  private final List<Segment> segmentTargets;
  List<Segment> rankedSegments;
  UserProfileModule userProfileModule;

  public RankModule(List<Segment> segmentTargets, UserProfileModule userProfileModule) {
    this.segmentTargets = segmentTargets;
    this.userProfileModule = userProfileModule;
    this.rankedSegments = new ArrayList<>();
  }

  public void rank() {
    double averageSpeed = userProfileModule.getAverageSpeed();

    double durationOfExercise = 1800; // s
    for (Segment segment : segmentTargets) {
      double ccRank = segment.getClimbCategory() / Math.max(1, userProfileModule.getClimbingCategoryAverage()) * 100;
      double duration = segment.distance / averageSpeed;
      segment.estimatedDuration = duration;
      System.out.println("durationOfExercise: " + durationOfExercise + "- duration: " + duration);
      double durationRank = Math.abs(durationOfExercise - duration) / durationOfExercise * 100;
      segment.rank = durationRank; // (1.5 * ccRank) + durationRank;
    }

    rankedSegments.addAll(segmentTargets);
    rankedSegments.sort(new Comparator<Segment>() {
      @Override
      public int compare(Segment o1, Segment o2) {
        return Double.valueOf(o1.rank).compareTo(o2.rank);
      }
    });
  }

  public List<Segment> getRankedSegments() {
    return rankedSegments;
  }
}
