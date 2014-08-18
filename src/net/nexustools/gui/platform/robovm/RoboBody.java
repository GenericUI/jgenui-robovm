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

import net.nexustools.gui.wrap.WBody;
import net.nexustools.gui.wrap.impl.NWidget;

public class RoboBody extends WBody {

	protected RoboBody(String tag, RoboPlatform platform) {
		super(tag, platform);
	}
	protected RoboBody(RoboPlatform platform) {
		this("Body", platform);
	}
	
	public RoboBody() {
		this(RoboPlatform.instance());
	}

	@Override
	protected NWidget createNative() {
		return null;
	}

}
