package com.project.api.data.model.spatial;

import java.awt.Point;
import java.util.List;

public class Route {
	private Point startPoint;
	private Point endPoint;
	private List<Point> stopPoints;
	
	public Point getStartPoint() {
		return startPoint;
	}
	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}
	public Point getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}
	public List<Point> getStopPoints() {
		return stopPoints;
	}
	public void setStopPoints(List<Point> stopPoints) {
		this.stopPoints = stopPoints;
	}
	
	
	
}
