package org.openmrs.module.xforms;

import java.util.List;

import org.openmrs.Form;
import org.openmrs.module.xforms.db.XformsDAO;
import org.openmrs.module.xforms.model.PatientMedicalHistory;
import org.openmrs.module.xforms.model.PersonRepeatAttribute;
import org.openmrs.module.xforms.model.XformUser;
import org.springframework.transaction.annotation.Transactional;


/**  
 * Service methods for the Xforms module
 */

@Transactional
public interface XformsService {
	
	/**
	 * Sets the xforms data access object.
	 * 
	 * @param dao - the data access object.
	 */
	public void setXformsDAO(XformsDAO dao);
	
	/**
	 * Saves an Xform in the database. If it already exists, it will be overwritten,
	 * else will create a new one.
	 * 
	 * @param xform
	 */
	public void saveXform(Xform xform);
	
	/**
	 * Deletes an XForm associated with the given form
	 * 
	 * @param form Form object 
	 */
	public void deleteXform(Form form);
	
	/**
	 * Deletes an XForm associated with the given form id
	 * 
	 * @param formId The id of the form
	 */
	public void deleteXform(Integer formId);
	
	/**
	 * Deletes an XForm to XHTML XSLT for a form of given form id
	 * 
	 * @param formId The id of the form
	 */
	public void deleteXslt(Integer formId);
	
	/**
	 * Get the XForm for the given form
	 * 
	 * @param form - the form
	 * @return XForm associated with the form
	 */
	@Transactional(readOnly=true)
	public Xform getXform(Form form);
	
	/**
	 * Gets all XForms
	 * 
	 * @return List of XForms
	 */
	@Transactional(readOnly=true)
	public List<Xform> getXforms();
	
	/**
	 * Get the XForm for the given form
	 * 
	 * @param formId id of the form that owns the XForm to retrieve
	 * @return XForm associated with the form
	 */
	@Transactional(readOnly=true)
	public Xform getXform(Integer formId);
	
	/**
	 * Gets the value of a patient table field.
	 * 
	 * @param patientId - the id of the patient.
	 * @param tableName - the name of the database table.
	 * @param columnName - the name of the database column.
	 * @param filterValue - the value of the filter.
	 * @return
	 */
	@Transactional(readOnly=true)
	public Object getPatientValue(Integer patientId, String tableName, String columnName, String filterValue);
	
	/**
	 * Gets a list of users.
	 * 
	 * @return - the user list.
	 */
	@Transactional(readOnly=true)
	public List<XformUser> getUsers();

	/**
	 * Gets a list of ids for forms having xforms.
	 * 
	 * @return - the formId list.
	 */
	@Transactional(readOnly=true)
	public List<Integer> getXformFormIds();
	
	/**
	 * Checks whether a form has an XForm stored in the database.
	 * 
	 * @param formId - the form id.
	 * @return true if it has, else false.
	 */
	@Transactional(readOnly=true)
	public boolean hasXform(Integer formId);
	
	/**
	 * Checks whether a form has an xslt stored in the database.
	 * 
	 * @param formId - the form id.
	 * @return true if it has, else false.
	 */
	@Transactional(readOnly=true)
	public boolean hasXslt(Integer formId);
	
	/**
	 * Get the xslt for the given form's XForm.
	 * 
	 * @param formId id of the form that owns the XForm whose xslt to retrieve.
	 * @return xslt of the XForm associated with the form.
	 */
	@Transactional(readOnly=true)
	public String getXslt(Integer formId);
	
	/**
	 * Saves an xform's xslt in the database. If it already exists, it will be overwritten,
	 * else will create a new one.
	 * 
	 * @param formId - the id of the form whose xform's xslt to save.
	 * @param xslt - the xslt to save.
	 */
	public void saveXslt(Integer formId, String xslt);
	
	/**
	 * Gets the default value of a form field.
	 * 
	 * @param formId - the id of the form.
	 * @param fieldName - the name of the field.
	 * @return the default value of the form field.
	 */
	@Transactional(readOnly=true)
	public Object getFieldDefaultValue(Integer formId, String fieldName);
	
	/**
	 * Get the XForm for the given form and can create a new one if none exists in the database.
	 * 
	 * @param formId - id of the form that owns the XForm to retrieve
	 * @param createNewIfNonExistant -  set to true to create a new xform if none exists in the database, else returns null.
	 * @return XForm associated with the form
	 */
	@Transactional(readOnly=true)
	public Xform getXform(Integer formId, boolean createNewIfNonExistant);
	
	/**
	 * Gets a new XForm for the given form. This Xform is created on the fly basing on
	 * the current form schema and template, instead of using an xform stored in
	 * the database which may not match with the current schema and template.
	 * 
	 * @param formId - id of the form that owns the XForm to retrieve
	 * @return XForm associated with the form
	 */
	@Transactional(readOnly=true)
	public Xform getNewXform(Integer formId);
	
	public List<PersonRepeatAttribute> getPersonRepeatAttributes(Integer personId, Integer personAttributeId);
	public void savePersonRepeatAttribute(PersonRepeatAttribute personRepeatAttribute);
	public void deletePersonRepeatAttribute(Integer personRepeatAttributeId);
	
	public List<Object[]> getList(String sql, String displayField, String valueField);
	
	public PatientMedicalHistory getPatientMedicalHistory(Integer patientId);
	
	public List<MedicalHistoryField> getMedicalHistoryFields();
	public void saveMedicalHistoryField(MedicalHistoryField field);
	public void deleteMedicalHistoryField(MedicalHistoryField field);
	public void deleteMedicalHistoryField(Integer fieldId);
}
