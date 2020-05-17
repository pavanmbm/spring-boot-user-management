package com.dhl.usermanagement.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * The Class Role.
 */
public class Role implements GrantedAuthority {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The name. */
	String name;

	/**
	 * Instantiates a new role.
	 */
	public Role() {
		// empty constructor
	}

	/**
	 * Instantiates a new role.
	 *
	 * @param name
	 *            the name
	 */
	public Role(String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.GrantedAuthority#getAuthority()
	 */
	@Override
	public String getAuthority() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

}