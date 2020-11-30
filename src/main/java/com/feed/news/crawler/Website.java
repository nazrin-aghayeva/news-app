package com.feed.news.crawler;

import com.feed.news.crawler.parsers.*;


public enum Website {


    TechCrunch(new TechCrunchParser()),
    UberGizmo(new UberGizmoParser()),
    DroidLife(new DroidLifeParser()),
    TechStartups(new TechStartupsParser()),
    LenovoNews(new LenovoNewsParser()),
    Insider(new InsiderParser()),
    HTCNews(new HTCNewsParser()),
    DigitIn(new DigitInParser()),
    Policy(new PolicyParser()),
    Habr(new HabrParser());

    public final JsoupParser parser;


    Website(JsoupParser parser ) {
        this.parser = parser;

    }

    public JsoupParser getParser() {
        return this.parser;
    }


}
