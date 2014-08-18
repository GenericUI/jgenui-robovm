/*
 * jgenui-robovm is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, version 3 or any later version.
 * 
 * jgenui-robovm is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with jgenui-robovm.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package net.nexustools.gui.platform.robovm;

import org.robovm.apple.dispatch.DispatchQueue;

import net.nexustools.gui.platform.Clipboard;
import net.nexustools.gui.platform.GUIPlatform;
import net.nexustools.gui.wrap.WPlatform;

public class RoboPlatform extends WPlatform {
	
    public static RoboPlatform instance() {
        return instance(true);
    }
    
    public static RoboPlatform instance(boolean exactMatch) {
        if(!exactMatch) {
            GUIPlatform currentPlatform = GUIPlatform.current();
            if(currentPlatform instanceof RoboPlatform)
                return (RoboPlatform)currentPlatform; // Good Enough
        }
        return (RoboPlatform)GUIPlatform.byClass(RoboPlatform.class);
    }

	public RoboPlatform() {
		super("iOS");
	}

	@Override
	protected void invokeAndWaitImpl(Runnable run) {
		DispatchQueue queue = RoboAppDelegate.getDispatchQueue();
		if(queue == null)
			run.run();
		else
			queue.sync(run);
	}

	@Override
	protected void invokeOnIdleImpl(Runnable run) {
		invokeLaterImpl(run);
	}

	@Override
	protected void invokeLaterImpl(Runnable run) {
		DispatchQueue queue = RoboAppDelegate.getDispatchQueue();
		if(queue == null)
			run.run();
		else
			queue.async(run);
	}

	@Override
	protected void populate(WidgetRegistry baseRegistry) {
	}

	@Override
	public boolean supports(Feature feature) {
		return false;
	}

	@Override
	public Clipboard clipboard() {
		return null;
	}

	@Override
	public void open(String url) {
	}

}
