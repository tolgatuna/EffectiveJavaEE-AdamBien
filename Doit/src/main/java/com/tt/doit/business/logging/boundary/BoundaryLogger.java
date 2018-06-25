package com.tt.doit.business.logging.boundary;

import com.tt.doit.business.monitoring.entity.CallEvent;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class BoundaryLogger {

    @Inject
    Event<CallEvent> monitoring;

    @AroundInvoke
    public Object logCall(InvocationContext invocationContext) throws Exception {
        long start = System.currentTimeMillis();
        try {
            return invocationContext.proceed();
        }  finally {
            long duration = System.currentTimeMillis() - start;
            this.monitoring.fire(new CallEvent(invocationContext.getMethod().getName(), duration));
        }
    }
}
