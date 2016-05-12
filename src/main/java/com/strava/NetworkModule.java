package com.strava;

import com.strava.models.Activity;
import com.strava.models.Segment;
import com.strava.models.SegmentEffort;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Network module.
 */
public class NetworkModule {
  private final String authorizationValue = "Bearer 66896397d38f7d77e77189a992d871ae3d2c0f8f";

  public BufferedReader getHttpsResponse(String url) {
    BufferedReader br = null;
    try {
      HttpsURLConnection httpsCon = (HttpsURLConnection) new URL(url).openConnection();
      httpsCon.setRequestProperty("Authorization", authorizationValue);
      br = new BufferedReader(new InputStreamReader(httpsCon.getInputStream()));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return br;
  }

  public List<Segment> getSegmentTargets(double latitude, double longitude) {
    List<Segment> segmentTargets = new ArrayList<>();
    try {
      BufferedReader br = getHttpsResponse("https://www.strava.com/api/v3/segment_targets?latlng=[" + latitude + "," + longitude + "]&activity_type=1");
      Optional<String> resp = br.lines().reduce(String::concat);
      if(resp.isPresent()){
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(resp.get());
        JSONObject json = (JSONObject)obj;
        JSONArray targets = (JSONArray)json.get("targets");
        for(int i = 0; i < targets.size(); ++i){
          JSONObject seg = (JSONObject)((JSONObject)(targets.get(i))).get("segment");
          double distance = (double)seg.get("distance");
          int climbCategory = ((Long)seg.get("climb_category")).intValue();
          JSONArray startLatLang = (JSONArray)seg.get("start_latlng");
          double startLat = (double)startLatLang.get(0);
          double startLong = (double)startLatLang.get(1);
          System.out.println("Segment #" + i + ": Dis: " + distance + ", CC: " + climbCategory + ", Lat: " + startLat + ", Long: " + startLong);
          segmentTargets.add(new Segment(distance, climbCategory, startLong, startLat));
        }
      }
    } catch (ParseException e) {
      e.printStackTrace();
    }

    return segmentTargets;
  }

  public List<Segment> getStarredSegments(int athleteId) {
    List<Segment> starredSegments = new ArrayList<>();
    try {
      BufferedReader br = getHttpsResponse("https://www.strava.com/api/v3/athletes/" + athleteId + "/segments/starred");
      Optional<String> resp = br.lines().reduce(String::concat);
      if(resp.isPresent()){
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(resp.get());
        JSONArray json = (JSONArray)obj;
        for(int i  = 0; i < json.size(); ++i){
          JSONObject seg = (JSONObject)(json.get(i));
          double distance = (double)seg.get("distance");
          int climbCategory = ((Long)seg.get("climb_category")).intValue();
          JSONArray startLatLang = (JSONArray)seg.get("start_latlng");
          double startLat = (double)startLatLang.get(0);
          double startLong = (double)startLatLang.get(1);
          starredSegments.add(new Segment(distance, climbCategory, startLong, startLat));
        }
      }
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return starredSegments;
  }

  public Activity getActivity(String activityId) {
    Activity activity = null;
    try {
      BufferedReader br = getHttpsResponse("https://www.strava.com/api/v3/activities/" + activityId);
      Optional<String> resp = br.lines().reduce(String::concat);
      if(resp.isPresent()){
        JSONParser parser = new JSONParser();
        JSONObject activityObj = (JSONObject) parser.parse(resp.get());
        JSONArray segmentEfforts = (JSONArray) activityObj.get("segment_efforts");
        List<SegmentEffort> segmentEffortList = new ArrayList<>();
        for (int i = 0; i < segmentEfforts.size(); ++i) {
          JSONObject segmentEffort = (JSONObject) segmentEfforts.get(i);
          int climbingCategory = (int) segmentEffort.getOrDefault("climb_category", 1);
          segmentEffortList.add(new SegmentEffort(climbingCategory));
        }
        activity = new Activity(segmentEffortList);
      }
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return activity;
  }

  public List<Activity> getActivities() {
    List<Activity> activities = new ArrayList<>();
    try {
      BufferedReader br = getHttpsResponse("https://www.strava.com/api/v3/athlete/activities");
      Optional<String> resp = br.lines().reduce(String::concat);
      if(resp.isPresent()){
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(resp.get());
        JSONArray json = (JSONArray)obj;
        for(int i  = 0; i < json.size(); ++i){
          JSONObject activity = (JSONObject)(json.get(i));
          long id = (long) activity.get("id");
          activities.add(getActivity("" + id));
        }
      }
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return activities;
  }
}