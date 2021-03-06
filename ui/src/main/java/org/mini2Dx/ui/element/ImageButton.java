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

import org.mini2Dx.core.graphics.TextureRegion;
import org.mini2Dx.core.serialization.annotation.ConstructorArg;
import org.mini2Dx.core.serialization.annotation.Field;
import org.mini2Dx.ui.render.ImageButtonRenderNode;
import org.mini2Dx.ui.render.ParentRenderNode;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Implementation of {@link Button} that only contains an image
 */
public class ImageButton extends Button {
	protected ImageButtonRenderNode renderNode;
	private TextureRegion textureRegion;
	
	@Field(optional=true)
	private String texturePath;
	@Field(optional = true)
	private String atlas;
	@Field(optional=true)
	private boolean responsive = false;
	
	/**
	 * Constructor. Generates a unique ID for this {@link ImageButton}
	 */
	public ImageButton() {
		super();
	}
	
	/**
	 * Constructor
	 * @param id The unique ID for this {@link ImageButton}
	 */
	public ImageButton(@ConstructorArg(clazz=String.class, name = "id") String id) {
		super(id);
	}
	
	/**
	 * Returns the current {@link TextureRegion} for this {@link ImageButton}
	 * 
	 * @param assetManager
	 *            The game's {@link AssetManager}
	 * @return Null if no {@link TextureRegion} has been set
	 */
	public TextureRegion getTextureRegion(AssetManager assetManager) {
		if (atlas != null) {
			if (texturePath != null) {
				TextureAtlas textureAtlas = assetManager.get(atlas, TextureAtlas.class);
				textureRegion = new TextureRegion(textureAtlas.findRegion(texturePath));
				texturePath = null;
			}
		} else if (texturePath != null) {
			textureRegion = new TextureRegion(assetManager.get(texturePath, Texture.class));
			texturePath = null;
		}
		return textureRegion;
	}

	/**
	 * Sets the current {@link TextureRegion} used by this {@link ImageButton}
	 * 
	 * @param textureRegion
	 *            The {@link TextureRegion} to use
	 */
	public void setTextureRegion(TextureRegion textureRegion) {
		this.textureRegion = textureRegion;
		
		if(renderNode == null) {
			return;
		}
		renderNode.setDirty(true);
	}
	
	/**
	 * Sets the current {@link Texture} used by this {@link ImageButton}
	 * 
	 * @param texture
	 *            The {@link Texture} to use
	 */
	public void setTexture(Texture texture) {
		setTextureRegion(new TextureRegion(texture));
	}
	
	/**
	 * Sets the current texture path. This will set the current
	 * {@link TextureRegion} by loading it via the {@link AssetManager}
	 * 
	 * @param texturePath
	 *            The path to the texture
	 */
	public void setTexturePath(String texturePath) {
		this.texturePath = texturePath;
		
		if(renderNode == null) {
			return;
		}
		renderNode.setDirty(true);
	}
	
	/**
	 * Returns the atlas (if any) to look up the texture in
	 * @return Null by default
	 */
	public String getAtlas() {
		return atlas;
	}

	/**
	 * Sets the atlas to look up the texture in
	 * @param atlas Null if the texture should not be looked up via an atlas
	 */
	public void setAtlas(String atlas) {
		this.atlas = atlas;
		
		if (renderNode == null) {
			return;
		}
		renderNode.setDirty(true);
	}

	/**
	 * Returns if the image should scale to the size of the {@link ImageButton}
	 * @return False by default
	 */
	public boolean isResponsive() {
		return responsive;
	}

	/**
	 * Sets if the image should scale to the size of the {@link ImageButton}
	 * @param responsive
	 */
	public void setResponsive(boolean responsive) {
		this.responsive = responsive;
		
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
		renderNode = new ImageButtonRenderNode(parentRenderNode, this);
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
	public void syncWithRenderNode() {
		while(!effects.isEmpty()) {
			renderNode.applyEffect(effects.poll());
		}
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
