package com.dnu.klimmenkov.projectplanner.aspect.pointcut;

import org.aspectj.lang.annotation.Pointcut;

public class LoggingPointcut {
    @Pointcut("!execution (* com.dnu.klimmenkov.projectplanner.service.impl.UserServiceImpl.findUserByLogin(..)) " +
            "&& !execution(* com.dnu.klimmenkov.projectplanner.service.impl.HistoryServiceImpl.getAllHistory()) " +
            "&& execution (* com.dnu.klimmenkov.projectplanner.service.impl.*.get*(..)) ")
    public void allGetMethods() {

    }

    @Pointcut("!execution (* com.dnu.klimmenkov.projectplanner.service.impl.HistoryServiceImpl.saveHistory(..)) " +
            "&& execution (* com.dnu.klimmenkov.projectplanner.service.impl.*.save*(..))")
    public void allSaveMethods() {

    }

    @Pointcut("!execution(* com.dnu.klimmenkov.projectplanner.service.impl.HistoryServiceImpl.deleteHistory(..)) " +
            "&& execution (* com.dnu.klimmenkov.projectplanner.service.impl.*.delete*(..))")
    public void allDeleteMethods() {

    }

    @Pointcut("allGetMethods() || allSaveMethods() || allDeleteMethods()")
    public void allShowAddDeleteMethods() {
    }
}
