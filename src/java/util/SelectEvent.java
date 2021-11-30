/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.faces.component.UIComponent;
import javax.faces.component.behavior.Behavior;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.AjaxBehaviorListener;
import javax.faces.event.FacesListener;

/**
 *
 * @author Adelino
 */
public class SelectEvent extends AjaxBehaviorEvent {

    private Object object;

    public SelectEvent(UIComponent component, Behavior behavior, Object object) {
        super(component, behavior);
        this.object = object;
    }

    @Override
    public boolean isAppropriateListener(FacesListener faceslistener) {
        return (faceslistener instanceof AjaxBehaviorListener);
    }

    @Override
    public void processListener(FacesListener faceslistener) {
        ((AjaxBehaviorListener) faceslistener).processAjaxBehavior(this);
    }

    public Object getObject() {
        return object;
    }

}
