/**
 * Copyright (c) 2015 See AUTHORS file
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name of the mini2Dx nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.mini2Dx.core.engine;

/**
 * A common interface for objects that can be sized with an x and y dimension
 */
public interface Sizeable {
	/**
	 * Returns the unique id of this object
	 * 
	 * @return
	 */
	public int getId();

	/**
	 * Returns the width of this object
	 * 
	 * @return 0 by default
	 */
	public float getWidth();

	/**
	 * Returns the height of this object
	 * 
	 * @return 0 by default
	 */
	public float getHeight();
	
	/**
	 * Returns min X coordinate of this object
	 * 
	 * @return The left-most x coordinate
	 */
	public float getMinX();
	
	/**
	 * Returns min Y coordinate of this object
	 * 
	 * @return The up-most y coordinate
	 */
	public float getMinY();
	
	/**
	 * Returns max X coordinate of this object
	 * 
	 * @return The right-most x coordinate
	 */
	public float getMaxX();
	
	/**
	 * Returns max Y coordinate of this object
	 * 
	 * @return The bottom-most y coordinate
	 */
	public float getMaxY();

	/**
	 * Adds a {@link SizeChangeListener} to be notified of size changes
	 * 
	 * @param listener
	 *            The {@link SizeChangeListener} to add
	 */
	public <T extends Sizeable> void addSizeChangeListener(SizeChangeListener<T> listener);

	/**
	 * Removes a {@link SizeChangeListener} to stop it being notified of size
	 * changes
	 * 
	 * @param listener
	 *            The {@link SizeChangeListener} to remove
	 */
	public <T extends Sizeable> void removeSizeChangeListener(SizeChangeListener<T> listener);
}
