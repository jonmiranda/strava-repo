package com.strava;

import com.strava.models.Segment;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RankModule {
  List<Segment> rankedSegments;
  UserProfileModule userProfileModule;

  public RankModule(UserProfileModule userProfileModule) {
    this.userProfileModule = userProfileModule;
    this.rankedSegments = new ArrayList<>();
  }

  public void rank(List<Segment> segmentTargets) {
    rankedSegments.clear();
    double averageSpeed = userProfileModule.getAverageSpeed();
//    List<Integer> climbingCategories = userProfileModule.getClimbingCategories();
    double ccAverage = Math.max(1, userProfileModule.getClimbingCategoryAverage());

    double durationOfExercise = 900; // s
    for (Segment segment : segmentTargets) {
      double ccRank = 1.0 - (Math.abs(1.0 - (segment.climbingCategory / ccAverage))); // 0.0 (worst) - 1.0 (best)
      double duration = segment.distance / averageSpeed;
      segment.estimatedDuration = duration;
      double durRank = 1.0 - (Math.abs(1.0 - (duration / durationOfExercise))); // 0.0 (worst) - 1.0 (best)
//      System.out.println("durationOfExercise: " + durationOfExercise + "- duration: " + duration);
//      double durationRank = 1 - (Math.abs(durationOfExercise - duration) / durationOfExercise * 100);
      segment.rank = 3 * ccRank + 2 * durRank;
    }

    rankedSegments.addAll(segmentTargets);
    rankedSegments.sort(new Comparator<Segment>() {
      @Override
      public int compare(Segment o1, Segment o2) {
        return Double.valueOf(o2.rank).compareTo(o1.rank);
      }
    });
  }

  public List<Segment> getRankedSegments() {
    return rankedSegments;
  }
}
