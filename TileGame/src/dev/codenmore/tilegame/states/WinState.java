package dev.codenmore.tilegame.states;

import java.awt.Graphics;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.gfx.Assets;
import dev.codenmore.tilegame.ui.ClickListener;
import dev.codenmore.tilegame.ui.UIImageButton;
import dev.codenmore.tilegame.ui.UIManager;

public class WinState extends State {

	private UIManager uiaManager;
	
	
	public WinState(Handler handler) {
		super(handler);
		uiaManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiaManager);
		
		
		
		uiaManager.addObject(new UIImageButton(201,250,348,126,Assets.btn_exit, new ClickListener(){

			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				System.exit(0);
			}}));
	}
	
	
	
	@Override
	public void tick() {
		uiaManager.tick();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.winScreen, 210, 0, null);
		uiaManager.render(g);
		
	}

	
}
