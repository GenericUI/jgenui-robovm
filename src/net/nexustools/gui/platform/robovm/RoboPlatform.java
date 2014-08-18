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

import net.nexustools.gui.platform.Clipboard;
import net.nexustools.gui.wrap.WPlatform;

public class RoboPlatform extends WPlatform {

	protected RoboPlatform(String name) {
		super(name);
		
	}

	@Override
	protected void invokeAndWaitImpl(Runnable run) {
	}

	@Override
	protected void invokeOnIdleImpl(Runnable run) {
	}

	@Override
	protected void invokeLaterImpl(Runnable run) {
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
