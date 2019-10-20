package com.project.enginee.event.service;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.parser.ParseException;

public interface IEventTrackingService {
    public void collectBiletixData() throws ParserConfigurationException, IOException, ParseException;
}
