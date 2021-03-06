/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.xforms.formentry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.hl7.handler.ADTA28Handler;
import org.openmrs.hl7.handler.ORUR01Handler;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.app.ApplicationException;
import ca.uhn.hl7v2.app.MessageTypeRouter;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.parser.EncodingNotSupportedException;
import ca.uhn.hl7v2.parser.GenericParser;

/**
 * Main entry point for processing of HL7 streams into OpenMRS
 * 
 * NOTE: This class has been copied from earlier versions of openmrs
 *       because it is no longer available in versions starting from 1.6
 *       This ensures that atleast for now, the xforms module works with all
 *       versions of openmrs from 1.2.2 up to the latest which is 1.6
 * 
 * @version 1.0
 */
public class HL7Receiver {
	
	Log log = LogFactory.getLog(HL7Receiver.class);
	
	private GenericParser parser;
	
	private MessageTypeRouter router;
	
	public HL7Receiver() {
		log.debug("Register handler applications for R01 and A28");
		
		parser = new GenericParser();
		// TODO draw registered applications from database or configuration file
		router = new MessageTypeRouter();
		router.registerApplication("ORU", "R01", new ORUR01Handler());
		router.registerApplication("ADT", "A28", new ADTA28Handler());
	}
	
	public Message processMessage(String hl7) throws Exception {
		
		// TODO: any pre-parsing for HL7 messages would go here
		
		// First, try and parse the message
		Message message;
		try {
			message = parser.parse(hl7);
		}
		catch (EncodingNotSupportedException e) {
			throw e;
			//throw new HL7Exception("HL7 encoding not supported", e);
		}
		catch (ca.uhn.hl7v2.HL7Exception e) {
			throw e;
			//throw new HL7Exception("Error parsing message", e);
		}
		
		// TODO: any post-parsing (pre-routing) processing would go here
		
		// If parsing succeeded, then try to route the message
		Message response;
		try {
			if (!router.canProcess(message))
				throw new HL7Exception("No route for message");
			response = router.processMessage(message);
		}
		catch (ApplicationException e) {
			throw e;
			//throw new HL7Exception("Error routing HL7 message", e);
		}
		return response;
	}
}

