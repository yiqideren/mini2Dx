/**
 * Copyright (c) 2016 See AUTHORS file
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name of the mini2Dx nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.mini2Dx.core.util;

import org.junit.Before;
import org.junit.Test;
import org.mini2Dx.core.exception.MdxException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessFiles;
import com.badlogic.gdx.files.FileHandle;

import junit.framework.Assert;

/**
 * Unit tests for {@link UriFileHandle}
 */
public class UriFileHandleTest {
	@Before
	public void setUp() {
		Gdx.files = new HeadlessFiles();
	}

	@Test
	public void testGetWithInternalPath() {
		FileHandle fileHandle = UriFileHandle.get("internal://textures/sprite.png");
		Assert.assertEquals("textures/sprite.png", fileHandle.path());
	}
	
	@Test(expected=NullPointerException.class)
	public void testGetWithNullString() {
		UriFileHandle.get(null);
	}
	
	@Test(expected=MdxException.class)
	public void testGetWithEmptyString() {
		UriFileHandle.get("");
	}
	
	@Test(expected=MdxException.class)
	public void testGetWithInvalidUriString() {
		UriFileHandle.get("textures/sprite.png");
	}
	
	@Test(expected=MdxException.class)
	public void testGetWithMissingSlashString() {
		UriFileHandle.get("internal:/textures/sprite.png");
	}
}
