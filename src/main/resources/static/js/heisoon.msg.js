var pending={"pair":"配对申请"};
var flashTitlePlayer = {
    start: function (msg) {
        this.title = parent.document.title;
        if (!this.action) {
            try {
                this.parent.element = document.getElementsByTagName('title')[0];
                this.parent.element.innerHTML = this.title;
                this.action = function (ttl) {
                    this.parent.element.innerHTML = ttl;
                };
            } catch (e) {
                this.action = function (ttl) {
                	parent.document.title = ttl;
                }
                delete this.element;
            }
            this.toggleTitle = function () {
                this.action('【' + this.messages[this.index = this.index == 0 ? 1 : 0] + '】');
            };
        }
        this.messages = [msg];
        var n = msg.length;
        var s = '';
        if (this.element) {
            var num = msg.match(/\w/g);
            if (num != null) {
                var n2 = num.length;
                n -= n2;
                while (n2 > 0) {
                    s += " ";
                    n2--;
                }
            }
        }
        while (n > 0) {
            s += '　';
            n--;
        };
        this.messages.push(s);
        this.index = 0;
        this.timer = setInterval(function () {
            flashTitlePlayer.toggleTitle();
        }, 1000);
    },
    stop: function () {
        if (this.timer) {
            clearInterval(this.timer);
            this.action(this.title);
            delete this.timer;
            delete this.messages;
        }
    }
};

function notify(title, content) {
        if(!title && !content){
            title = "桌面提醒";
            content = "您看到此条信息桌面提醒设置成功";
        }
        var iconUrl = "/static/images/heisoon.png";
        if (window.webkitNotifications) {
            //chrome老版本
            if (window.webkitNotifications.checkPermission() == 0) {
                var notif = window.webkitNotifications.createNotification(iconUrl, title, content);
                notif.display = function() {}
                notif.onerror = function() {}
                notif.onclose = function() {}
                notif.onclick = function() {this.cancel();}
                notif.replaceId = 'Meteoric';
                notif.show();
            } else {
                window.webkitNotifications.requestPermission($jy.notify);
            }
        }
        else if("Notification" in window){
            // 判断是否有权限
            if (Notification.permission === "granted") {
                var notification = new Notification(title, {
                    "icon": iconUrl,
                    "body": content,
                    "buttons:":[{"title":"确认"}]
                });
            }
            //如果没权限，则请求权限
            else if (Notification.permission !== 'denied') {
                Notification.requestPermission(function(permission) {
                    // Whatever the user answers, we make sure we store the
                    // information
                    if (!('permission' in Notification)) {
                        Notification.permission = permission;
                    }
                    //如果接受请求
                    if (permission === "granted") {
                        var notification = new Notification(title, {
                            "icon": iconUrl,
                            "body": content,
                        });
                    }
                });
            }else{
            	
            	//alert(Notification.permission)
            }
        }
}
