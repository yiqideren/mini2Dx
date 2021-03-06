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
package org.mini2Dx.ui.element;

import org.mini2Dx.core.serialization.annotation.ConstructorArg;
import org.mini2Dx.core.serialization.annotation.Field;
import org.mini2Dx.ui.layout.HorizontalAlignment;
import org.mini2Dx.ui.render.ParentRenderNode;
import org.mini2Dx.ui.render.TextButtonRenderNode;

/**
 * Implementation of {@link Button} that only contains text
 */
public class TextButton extends Button {
	protected TextButtonRenderNode renderNode;
	
	@Field(optional=true)
	private String text = "";
	@Field(optional=true)
	private HorizontalAlignment textAlignment = HorizontalAlignment.CENTER;
	
	/**
	 * Constructor. Generates a unique ID for this {@link TextButton}
	 */
	public TextButton() {
		this(null);
	}
	
	/**
	 * Constructor
	 * @param id The unique ID for this {@link TextButton}
	 */
	public TextButton(@ConstructorArg(clazz=String.class, name = "id") String id) {
		super(id);
	}
	
	/**
	 * Returns the text of this {@link TextButton}
	 * @return An empty {@link String} by default
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the text of this {@link TextButton}
	 * @param text A non-null {@link String}
	 */
	public void setText(String text) {
		if(text == null) {
			return;
		}
		this.text = text;
		
		if(renderNode == null) {
			return;
		}
		renderNode.setDirty(true);
	}

	@Override
	public void attach(ParentRenderNode<?, ?> parentRenderNode) {
		if(renderNode != null) {
			return;
		}
		renderNode = new TextButtonRenderNode(parentRenderNode, this);
		parentRenderNode.addChild(renderNode);
	}

	@Override
	public void detach(ParentRenderNode<?, ?> parentRenderNode) {
		if(renderNode == null) {
			return;
		}
		parentRenderNode.removeChild(renderNode);
		renderNode = null;
	}
	
	@Override
	public void setVisibility(Visibility visibility) {
		if(this.visibility == visibility) {
			return;
		}
		this.visibility = visibility;
		
		if(renderNode == null) {
			return;
		}
		renderNode.setDirty(true);
	}
	
	@Override
	public void setStyleId(String styleId) {
		if(styleId == null) {
			return;
		}
		if(this.styleId.equals(styleId)) {
			return;
		}
		this.styleId = styleId;
		
		if(renderNode == null) {
			return;
		}
		renderNode.setDirty(true);
	}
	
	@Override
	public void setZIndex(int zIndex) {
		this.zIndex = zIndex;
		
		if(renderNode == null) {
			return;
		}
		renderNode.setDirty(true);
	}
	
	@Override
	public void syncWithRenderNode() {
		while(!effects.isEmpty()) {
			renderNode.applyEffect(effects.poll());
		}
	}

	/**
	 * Returns the {@link HorizontalAlignment} of the button's text
	 * @return {@link HorizontalAlignment#CENTER} by default
	 */
	public HorizontalAlignment getTextAlignment() {
		return textAlignment;
	}

	/**
	 * Sets the {@link HorizontalAlignment} of the button's text
	 * @param textAlignment The text alignment
	 */
	public void setTextAlignment(HorizontalAlignment textAlignment) {
		if(textAlignment == null) {
			return;
		}
		this.textAlignment = textAlignment;
	}
	
	@Override
	public void setLayout(String layout) {
		if(layout == null) {
			return;
		}
		this.layout = layout;
		if(renderNode == null) {
			return;
		}
		renderNode.setDirty(true);
	}
}
