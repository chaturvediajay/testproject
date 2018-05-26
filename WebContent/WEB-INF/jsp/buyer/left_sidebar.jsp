<aside id="leftsidebar" class="sidebar">
            <!-- User Info -->
            <div class="user-info">
                <div class="image">
                    <img src="images/user.png" width="48" height="48" alt="User">
                </div>
                <div class="info-container">
                    <div class="name" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">${sessionScope.loginSession.name}</div>
                    <div class="email">${sessionScope.loginSession.email}</div>
                    <div class="btn-group user-helper-dropdown">
                        <i class="material-icons" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">keyboard_arrow_down</i>
                        <ul class="dropdown-menu pull-right">
                            <li><a href="javascript:void(0);" class=" waves-effect waves-block"><i class="material-icons">person</i>Profile</a></li>
                            <li role="seperator" class="divider"></li>
                            <li><a href="javascript:void(0);" class=" waves-effect waves-block"><i class="material-icons">group</i>Followers</a></li>
                            <li><a href="javascript:void(0);" class=" waves-effect waves-block"><i class="material-icons">shopping_cart</i>Sales</a></li>
                            <li><a href="javascript:void(0);" class=" waves-effect waves-block"><i class="material-icons">favorite</i>Likes</a></li>
                            <li role="seperator" class="divider"></li>
                            <li><a href="${pageContext.request.contextPath}/logout"><i class="material-icons">input</i>Sign Out</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <!-- #User Info -->
            <!-- Menu -->
            <div class="menu">
                <div class="slimScrollDiv" style="position: relative; overflow: hidden; width: auto; height: 361px;"><ul class="list" style="overflow: hidden; width: auto; height: 361px;">
                    <li class="header">MAIN NAVIGATION</li>
                    <li class="active">
                        <a href="${pageContext.request.contextPath}/buyer/" class="toggled waves-effect waves-block">
                            <span>My Profile</span>
                        </a>
                    </li>
                     <li>
                        <a href="${pageContext.request.contextPath}/buyer/account" class=" waves-effect waves-block">
                            <span>Account</span>
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/buyer/orderList" class=" waves-effect waves-block">
                            <span>Orders</span>
                        </a>
                    </li>
                    
                    <li>
                        <a href="${pageContext.request.contextPath}/buyer/changePassword" class=" waves-effect waves-block">
                            <span>Change Password</span>
                        </a>
                    </li>
                </ul><div class="slimScrollBar" style="background: rgba(0, 0, 0, 0.5); width: 4px; position: absolute; top: 0px; opacity: 0.4; display: none; border-radius: 0px; z-index: 99; right: 1px; height: 140.13px;"></div><div class="slimScrollRail" style="width: 4px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 0px; background: rgb(51, 51, 51); opacity: 0.2; z-index: 90; right: 1px;"></div></div>
            </div>
            <!-- #Menu -->
            <!-- Footer -->
            <div class="legal">
                <div class="copyright">
                    © 2018 - 2019 <a href="javascript:void(0);">chouhan rugs</a>.
                </div>
                <div class="version">
                    <b>Version: </b> 1.0.2
                </div>
            </div>
            <!-- #Footer -->
        </aside>