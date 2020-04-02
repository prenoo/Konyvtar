package com.konyvtar.tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;


@ManagedBean
@SessionScoped
public class TagController {
	
	private List<Tag> tagok;
	private TagDbUtil tagDbUtil;
	private Logger logger = Logger.getLogger(getClass().getName());
	
	public TagController() throws Exception {
		tagok = new ArrayList<>();
		tagDbUtil = TagDbUtil.getInstance();
	}
	
	public List<Tag> getTagok() {
		return tagok;
	}
	
	public void loadTagok() {
		
		logger.info("Loading tagok");
	
		tagok.clear();
		
		try {
			// get all tag from database
			tagok = tagDbUtil.getTagok();
		} catch (Exception e) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error loading tag table", e);
			
			// add error message for JSF page
			addErrorMessage(e);
		}
	
	}
	
	public String addTag(Tag theTag) {

		logger.info("Adding tag: " + theTag);

		try {
			
			// add tag to the database
			tagDbUtil.addTag(theTag);
			
		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error adding tags", exc);
			
			// add error message for JSF page
			addErrorMessage(exc);

			return null;
		}
		
		return "list-tag?faces-redirect=true";
	}
	
	public String loadTag(int tagId) {
		
		logger.info("loading tag: " + tagId);
		
		try {
			// get tag from database
			Tag theTag = tagDbUtil.getTag(tagId);
			
			// put in the request attribute ... so we can use it on the form page
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();		

			Map<String, Object> requestMap = externalContext.getRequestMap();
			requestMap.put("tag", theTag);	
			
		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error loading tag id:" + tagId, exc);
			
			// add error message for JSF page
			addErrorMessage(exc);
			
			return null;
		}
				
		return "update-tag-form.xhtml";
	}
	
	
	public String updateTag(Tag theTag) {

		logger.info("updating tag: " + theTag);
		
		try {
			
			// update tag in the database
			tagDbUtil.updateTag(theTag);
			
		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error updating tag: " + theTag, exc);
			
			// add error message for JSF page
			addErrorMessage(exc);
			
			return null;
		}
		
		return "list-tag?faces-redirect=true";		
	}
	
	public String deleteTag(int tagId) {

		logger.info("Deleting tag id: " + tagId);
		
		try {

			// delete the tag from the database
			tagDbUtil.deleteTag(tagId);
			
		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error deleting tag id: " + tagId, exc);
			
			// add error message for JSF page
			addErrorMessage(exc);
			
			return null;
		}
		
		return "list-tag";	
	}	
	
	private void addErrorMessage(Exception exc) {
		FacesMessage message = new FacesMessage("Error: " + exc.getMessage());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	

}
