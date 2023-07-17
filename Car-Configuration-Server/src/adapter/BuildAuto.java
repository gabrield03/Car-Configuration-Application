/*
 * Author: Gabriel Larot
 * Date: June 8, 2023
*/

package adapter;

import server.AutoServer;

public class BuildAuto extends ProxyAuto implements CreateAuto, UpdateAuto, FixAuto, ChoiceAuto, EditAuto, AutoServer, Debuggable {}