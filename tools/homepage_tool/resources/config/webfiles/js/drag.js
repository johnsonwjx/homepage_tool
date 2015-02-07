//===========    拖动元素 开始    ===================
function InitDragDrop() {
	var _drag_startX = 0; // mouse starting positions
	var _drag_startY = 0;
	var _drag_offsetX = 0; // current element offset
	var _drag_offsetY = 0;
	var _drag_element; // needs to be passed from OnMouseDown to OnMouseMove
	
	function OnMouseDown(e) {
		// IE is retarded and doesn't pass the event object
		if (e == null)
			e = window.event;

		// IE uses srcElement, others use target
		var target = e.target != null ? e.target : e.srcElement;
		if(!target) return;
		
		var tagName = target.tagName.toLowerCase();
		if('h1,h2,h3,h4,div,table,tr,td,span'.indexOf(tagName) < 0){// 只能拖动这些元素
			return;
		}
		
		target = findDraggableParent(target);
		if(!target) return;
		
		try {
			pause_resume(true,false);
		} catch (e) {
		}
		
		// for IE, left click == 1
		// for Firefox, left click == 0
		if ((e.button == 1 && window.event != null || e.button == 0)) {
			// grab the mouse position
			_drag_startX = e.clientX;
			_drag_startY = e.clientY;

			// grab the clicked element's position
			_drag_offsetX = ExtractNumber(target.style.left);
			_drag_offsetY = ExtractNumber(target.style.top);

			// we need to access the element in OnMouseMove
			_drag_element = target;

			// tell our code to start moving the element with the mouse
			document.onmousemove = OnMouseMove;

			// cancel out any text selections
			document.body.focus();

			// prevent text selection in IE
			document.onselectstart = function() {
				return false;
			};
			// prevent IE from trying to drag an image
			target.ondragstart = function() {
				return false;
			};

			// prevent text selection (except IE)
			return false;
		}
	}

	function ExtractNumber(value) {
		var n = parseInt(value);
		return n == null || isNaN(n) ? 0 : n;
	}

	function OnMouseMove(e) {
		if (e == null)
			e = window.event;

		// this is the actual "drag code"
		_drag_element.style.left = (_drag_offsetX + e.clientX - _drag_startX) + 'px';
		_drag_element.style.top = (_drag_offsetY + e.clientY - _drag_startY) + 'px';
	}

	function OnMouseUp(e) {
		if (_drag_element != null) {

			// we're done with these events until the next OnMouseDown
			document.onmousemove = null;
			document.onselectstart = null;
			_drag_element.ondragstart = null;

			// this is how we know we're not dragging
			_drag_element = null;
		}
	}

	// 找到对应的包含drag class的父级元素
	function findDraggableParent(node) {
		while (node != null) {
			if ((' ' + node.className + ' ').indexOf(' drag ') > -1) {
				return node;
			}
			node = node.parentNode;
		}
	}
	
	document.onmousedown = OnMouseDown;
	document.onmouseup = OnMouseUp;
}


//===========    拖动元素 结束    ===================