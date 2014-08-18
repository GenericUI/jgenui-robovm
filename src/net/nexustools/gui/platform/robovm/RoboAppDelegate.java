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
import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.foundation.NSDictionary;
import org.robovm.apple.foundation.NSString;
import org.robovm.apple.uikit.UIApplication;
import org.robovm.apple.uikit.UIApplicationDelegateAdapter;

import static net.nexustools.Application.defaultName;
import static net.nexustools.Application.defaultOrganization;
import net.nexustools.concurrent.Prop;
import net.nexustools.concurrent.PropAccessor;
import net.nexustools.concurrent.logic.IfWriter;
import net.nexustools.gui.wrap.WAppDelegate;
import net.nexustools.utils.log.Logger;

public abstract class RoboAppDelegate<B extends RoboBody, P extends RoboPlatform> extends WAppDelegate<B, P> {
	
	private final static Prop<DispatchQueue> dispatchQueue = new Prop();
	public static class UIDelegate extends UIApplicationDelegateAdapter {
		@Override
		public boolean didFinishLaunching(UIApplication application, NSDictionary<NSString, ?> launchOptions) {
			Logger.debug("UIApplication", "didFinishLaunching");
			dispatchQueue.set(DispatchQueue.getCurrentQueue());
			return true;
		}
	}

    protected RoboAppDelegate(String[] args, P platform) {
        this(args, defaultName(), defaultOrganization(), platform);
    }
    protected RoboAppDelegate(String[] args, String name, String organization) {
        this(args, name, organization, (P)RoboPlatform.instance());
    }
    protected RoboAppDelegate(final String[] args, String name, String organization, P platform) {
        super(args, name, organization, platform);
    }
    
    @Override
    protected B create() {
    	return (B) new RoboBody();
    }

    @Override
    public void mainLoop(String[] args) {
    	Thread myself = Thread.currentThread();
    	String oldName = myself.getName();
    	myself.setName("RoboVM-MainLoop");

    	Logger.debug("Entering Main UI Loop");
        NSAutoreleasePool pool = new NSAutoreleasePool();
        UIApplication.main(args, null, UIDelegate.class);
        pool.close();
    	Logger.debug("Exited Main UI Loop");
        
        myself.setName(oldName);
    }
    
    public static void waitForDispatchQueue() {
    	while(!dispatchQueue.isset())
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {}
    }
    
	public static DispatchQueue getDispatchQueue() {
		DispatchQueue queue = dispatchQueue.get();
		if(queue == DispatchQueue.getCurrentQueue())
			return null;
		return queue;
	}

    @Override
    public boolean needsMainLoop() {
        return true;
    }


}
