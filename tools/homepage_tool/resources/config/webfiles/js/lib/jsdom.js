/**
 *
 *  对象模式创建模版
 *
 *  @param {Array} attrs 生成的节点数组
 *         @param {String} type 类型
 *         @param {Array|Object} attr 属性
 *         @param {Array|Object} child  子节点
 *         @param {Number} num  子节生成个数
 *         @param {Function} func  处理函数
 *         @param {Array} data  数据
 *
 *     @param {Element|String} target
 */
var tpl = function(ats, target) {
    target = fast.id(target);
    if (fast.isArray(ats) && ats.length > 0 && target.appendChild) {
        for (var i = 0, len = ats.length; i < len; i++) {
            var attrs = ats[i], tag = attrs.tag, attr = attrs.attr || {}, data = attrs.data, func = attrs.func, child = attrs.child, num = attrs.num ? attrs.num : 1, j = 0;
            var fragment = document.createDocumentFragment();
            for (; j < num; j++) {
                var isFunc = false;
                if (data) {
                    if (child) {
                        if (fast.isArray(child)) {
                            for (var k = 0, l = child.length; k < l; k++) {
                                child[k].data = data[j];
                            }
                        } else {
                            child.data = data[j];
                        }
                    } else {
                        if (func) {
                            attr = func(j, attr, data);
                            isFunc = true;
                        } else {
                            data = fast.values(data);
                            attr.text = data[j];
                        }
                    }
                }
                (isFunc === false) && func && ( attr = func(j, attr, data));
                var nodes = fast.node(tag, attr);
                fragment.appendChild(nodes);
                child && tpl(child, nodes);
            }
            target.appendChild(fragment);
        }
    }
};




var doc = window.document, _toString = Object.prototype.toString;

var fast = {
    isString : function(obj) {
        return !!(obj === '' || (obj && obj.charCodeAt && obj.substr));
    },
    isNumber : function(obj) {
        return _toString.call(obj) === '[object Number]';
    },
    isArray : [].isArray ||
    function(obj) {
        return _toString.call(obj) === '[object Array]';
    },
    isObject : function(obj) {
        return obj == null ? String(obj) == 'object' : _toString.call(obj) === '[object Object]' || true;
    },
    isEmptyObject : function(obj) {
        for (var name in obj) {
            return false;
        }
        return true;
    },
    getID : function() {
        var num1 = new Date().getTime();
        var num2 = parseInt(Math.random() * 100000, 10);
        return num1 + num2;
    },
    id : function(id) {
        if (this.isString(id)) {
            return doc.getElementById(id);
        } else if (id.nodeType) {
            return id;
        }
        return;
    },
    html : function(el, html) {
        el = this.id(el);
        if (html) {
            if (el != null && 'innerHTML' in el) {
                el.innerHTML = html;
            }
        } else {
            return el.innerHTML;
        }
    },
    values : function(obj) {
        var ret = [];
        for (var key in obj) {
            ret.push(obj[key]);
        }
        return ret;
    },
    setCssText : function(el, cssText) {
        el.style.cssText = cssText;
    },
    setAttr : function(element, attrObj) {
        var me = this, mapObj = {
            "class" : function() {
                element.className = attrObj["class"];
            },
            "style" : function() {
                me.setCssText(element, attrObj["style"]);
            },
            "text" : function() {
                if (attrObj["text"].nodeType) {
                    element.appendChild(attrObj["text"]);
                } else {
                    element.appendChild(document.createTextNode(attrObj["text"]));
                }
            }
        }
        for (p in attrObj) {
            if (mapObj[p]) {
                mapObj[p]();
            } else {
                element.setAttribute(p, attrObj[p]);
            }
        }
    },
    node : function(type, attrObj) {
        var element = doc.createElement(type);
        if (!this.isEmptyObject(attrObj)) {
            this.setAttr(element, attrObj);
        }
        return element;
    },
    testTime : function(get_as_float) {
        var now = new Date().getTime() / 1000;
        var s = parseInt(now, 10);
        return (get_as_float) ? now : (Math.round((now - s) * 1000) / 1000) + ' ' + s;
    },
    //ext**********************************/
    _indexOf : Array.prototype.indexOf,
    inArray : function(elem, arr, i) {
        var len;
        if (arr) {
            if (this._indexOf) {
                return this._indexOf.call(arr, elem, i);
            }
            len = arr.length;
            i = i ? i < 0 ? Math.max(0, len + i) : i : 0;
            for (; i < len; i++) {
                if ( i in arr && arr[i] === elem) {
                    return i;
                }
            }
        }
        return -1;
    },
    isDate : function(o) {
        return (null != o) && !isNaN(o) && ("undefined" !== typeof o.getDate);
    },
    Format : {},
    decodeHTML : function(str) {
        str = String(str).replace(/&quot;/g, '"').replace(/&lt;/g, '<').replace(/&gt;/g, '>').replace(/&amp;/g, "&");
        //处理转义的中文和实体字符
        return str.replace(/&#([\d]+);/g, function(_0, _1) {
            return String.fromCharCode(parseInt(_1, 10));
        });
    },
    apply : function(object, config, defaults) {
        if (defaults) {
            this.apply(object, defaults);
        }
        var enumerables = true, enumerablesTest = {
            toString : 1
        };
        for (i in enumerablesTest) {
            enumerables = null;
        }
        if (enumerables) {
            enumerables = ['hasOwnProperty', 'valueOf', 'isPrototypeOf', 'propertyIsEnumerable', 'toLocaleString', 'toString', 'constructor'];
        }
        if (object && config && typeof config === 'object') {
            var i, j, k;

            for (i in config) {
                object[i] = config[i];
            }

            if (enumerables) {
                for ( j = enumerables.length; j--; ) {
                    k = enumerables[j];
                    if (config.hasOwnProperty(k)) {
                        object[k] = config[k];
                    }
                }
            }
        }

        return object;
    }
};



var data = [
	{name : "test1",sex : "man",age : "20",date : "2011-10-13 12:00:00:0",uid : "1"}, 
	{name : "test2",sex : "man",age : "20",date : "2011-10-13 12:00:00:0",uid : "2"}, 
	{name : "test3",sex : "man",age : "20",date : "2011-10-13 12:00:00:0",uid : "3"}, 
	{name : "test4",sex : "man",age : "20",date : "2011-10-13 12:00:00:0",uid : "4"}, 
	{name : "test5",sex : "man",age : "20",date : "2011-10-13 12:00:00:0",uid : "5"}, 
	{name : "test6",sex : "man",age : "20",date : "2011-10-13 12:00:00:0",uid : "6"}, 
	{name : "test7",sex : "man",age : "20",date : "2011-10-13 12:00:00:0",uid : "7"}, 
	{name : "test8",sex : "man",age : "20",date : "2011-10-13 12:00:00:0",uid : "8"}, 
	{name : "test9",sex : "man",age : "20",date : "2011-10-13 12:00:00:0",uid : "9"}, 
	{name : "test10",sex : "man",age : "20",date : "2011-10-13 12:00:00:0",uid : "10"}
];

for(var i = 10; i < 10; i++){
    data.push({name : "test"+i,sex : "man",age : "20",date : "2011-10-13 12:00:00:0",uid : i});
}



/*
 *  字符串模式创建模版 拼音
 *
 */
var tp = function(str, data) {	
    var str = fast.id(str) ? fast.html(str) : str;
	str = str.replace(/<\#(\s|\S)*?\#>/g, function(p) {return p.replace(/("|\\)/g, "\\$1").replace("<#", '_s.push("').replace("#>", '");').replace(/<\%([\s\S]*?)\%>/g, '",$1,"')}).replace(/\r|\n/g, ""), keys = [], values = [], i;
    for (i in data) {
        keys.push(i);
        values.push(data[i]);
    }
	alert(str);
    return (new Function(keys, "var _s=[];" + str + " return _s;") ).apply(null, values).join("");
};