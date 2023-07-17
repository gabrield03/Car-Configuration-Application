/*
 * Author: Gabriel Larot
 * Date: June 8, 2023
*/

package adapter;

import client.AutoClient;

public class BuildAuto extends ProxyAuto implements CreateAuto, UpdateAuto, FixAuto, ChoiceAuto, EditAuto, AutoClient, Debuggable {}