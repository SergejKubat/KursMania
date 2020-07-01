package com.kursmania.utils;

import com.kursmania.jpa.entities.KursTag;
import com.kursmania.jpa.entities.Tag;
import com.kursmania.sessions.TagFacade;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;

public class Utilities {

    @EJB
    TagFacade tagFacade;

    public String getShortenText(String text, int numOfWords) {
        String[] reci = text.split(" ");
        if (reci.length < numOfWords) {
            return text;
        }
        StringBuilder noviText = new StringBuilder();
        for (int i = 0; i < numOfWords; i++) {
            noviText.append(reci[i]).append(" ");
        }
        noviText.deleteCharAt(noviText.length() - 1);
        return noviText.append("...").toString();
    }

    public String numberToTime(int number) {
        int hours, minutes, seconds;
        StringBuilder time = new StringBuilder();
        hours = number / 3600;
        minutes = (number % 3600) / 60;
        seconds = number - (hours * 3600 + minutes * 60);
        if (hours > 0) {
            if (hours < 10) {
                time.append("0").append(hours).append(":");
            } else {
                time.append(hours).append(":");
            }
        }
        if (minutes > 0) {
            if (minutes < 10) {
                time.append("0").append(minutes).append(":");
            } else {
                time.append(minutes).append(":");
            }
        }
        if (seconds > 0) {
            if (seconds < 10) {
                time.append("0").append(seconds);
            } else {
                time.append(seconds);
            }
        }
        return time.toString();
    }

    public <T> List<T> paginationHelper(int pageIndex, int itemsPerPage, List<T> list) {
        int startIndex, endIndex;
        startIndex = (pageIndex - 1) * itemsPerPage;
        endIndex = pageIndex * itemsPerPage;
        if (startIndex >= list.size()) {
            return null;
        }
        if (endIndex >= list.size()) {
            endIndex = list.size();
        }
        return list.subList(startIndex, endIndex);
    }

    public boolean isContainsTag(String tag, Collection<KursTag> tagovi) {
        for (KursTag kt : tagovi) {
            Tag t = tagFacade.find(kt.getTagId().getTagId());
            if (t.getTagIme().equals(tag)) {
                return true;
            }
        }
        return false;
    }

}
