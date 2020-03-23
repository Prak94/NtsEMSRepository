/**
 * 
 */
package org.com.nts.ems.custom.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author chetan
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class EmsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;

	private String statusCode;

}
