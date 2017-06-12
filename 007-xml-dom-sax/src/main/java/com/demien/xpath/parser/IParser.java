package com.demien.xpath.parser;

import com.demien.xpath.model.TownReportVO;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 04.03.14
 * Time: 21:30
 * To change this template use File | Settings | File Templates.
 */
public interface IParser {
    TownReportVO parse(String data);
}
