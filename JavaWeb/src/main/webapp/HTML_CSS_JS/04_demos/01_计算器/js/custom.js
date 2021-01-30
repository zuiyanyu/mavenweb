//Custom Javascript File

Calculator = function(options){
		self = this;
		self.options = options;
		self.options.calcScreenFont = document.defaultView.getComputedStyle(self.options.calcScreen).getPropertyValue('font-size');
		self.initCalc();		
};

Calculator.prototype = {

	dM : function (toLog) {
		//Global debug logging
		if(self.options.debug){
			console.log(toLog);			
		}
		
	},
	//循环theOjb对象，查找是否有matchProp属性
	objLoop : function (theObj, matchProp) {
        for(x in theObj){
            if(theObj[x] === matchProp){
                return true;
            }
        };
        return false;
	},

	//Loop Through Elements and Attach an Event Handler
	loopElements : function (ele, event, callBack) {
	    for(var i = 0, len = ele.length; i < len; i++){
	        ele[i].addEventListener(event, callBack);
	    };
	},
	//设置输出屏幕calScreen的值
	calcReset : function (resetVal) {
		self.options.calcScreen.value = resetVal;
	},
//-----------------------------------------------------------------
	//实现回退键功能
	deleteLast : function () {
		if(self.options.calcScreen.value != '0'){
            //self.options.calcScreen.value为屏幕输出值
            var str=self.options.calcScreen.value;
            str=str.substr(0,str.length-1);
            self.calcReset(str);
		}
	},

	suprise : function (triggerVals, triggerMatch) {

		var cssProps = document.defaultView.getComputedStyle(self.options.calc, null).getPropertyValue('transform');

		self.dM(cssProps);

		if(self.objLoop(triggerVals, triggerMatch) && cssProps !== 'matrix(-1, 0, 0, -1, 0, 0)'){
			self.options.calc.style.transform = 'rotate(180deg)';
			self.options.calc.style.transition = 'all 1s ease-in-out 0s';
		} else if(cssProps === 'matrix(-1, 0, 0, -1, 0, 0)') {
			self.options.calc.style.transform = 'rotate(0deg)';
			self.options.calc.style.transition = 'all 1s ease-in-out 0s';
		};
	},

	keyboardHandeling : function () {
		window.addEventListener('keypress', function (event, callback){
			var keyChar = String.fromCharCode(event.keyCode || event.charCode);
			self.dM(keyChar);
			if (self.objLoop(self.options.keys, keyChar)) {
				if(self.options.calcScreen.value === '0' || self.objLoop(self.options.operators, self.options.calcScreen.value)){
				keyChar === '.' ? self.calcReset('0') : self.calcReset('');	
				}
				self.calcReset(self.options.calcScreen.value + keyChar);
			}
		});
	},


	initCalc : function() {
		//Reset the calculator to'0' on page load
		self.calcReset('0');
		self.keyboardHandeling();

		self.loopElements(self.options.buttons, 'click', function () {
			//Set var with li's value
			var curButVal = this.childNodes[0].nodeValue;

			//If the clicked li has a class name of 'num' run the calcReset function 
			//passing the cur screen value and clicked button value in.
				
			if(this.className.indexOf('num') != -1 || this.className.indexOf('operator') != -1){
				//If the screen value is '0' remove it before adding the new values
				
				if(self.options.calcScreen.value === '0' || self.objLoop(self.options.operators, self.options.calcScreen.value)){
					curButVal === '.' ? self.calcReset('0') : self.calcReset('');
				}
				self.calcReset(self.options.calcScreen.value + curButVal);
			}

			//DELETE BUTTON
			if(this.getAttribute('id') === 'delete') {
				if(self.options.calcScreen.value.length === 1) {
					self.calcReset('0');
					return;
				}
				self.deleteLast();	
			};

			//Clear self.options.buttons
			if(this.className.indexOf('reset') != -1) {
				self.calcReset('0');
			};

			//EQUALS BUTTON
			if(this.getAttribute('id') === 'equals') {

				var screenChar = self.options.calcScreen.value.charAt(self.options.calcScreen.value.length -1);

				if(self.options.calcScreen.value === '0' || self.objLoop(self.options.operators, screenChar)) {
					confirm('Can you actually use a calculator?');
					return;
				}
//-----------------------------------------------------------------------------------------------------
                //self.options.calcScreen.value为屏幕输出值
                //使用self.calcReset显示计算结果
//------------------------------------------------------------------------------------------------------------------
                var result=eval(self.options.calcScreen.value);
                self.calcReset(result);
                self.suprise(self.options.supriseItems, self.options.calcScreen.value)

			}

		}); //loopElements ENDS
		
	}	
}

window.onload = function () {

		//Instantiation
		calculator =  new Calculator({
			calc : document.getElementById('calculator'),
			calcScreen : document.getElementById('screen'),
			buttons : document.getElementsByTagName('li'),
			supriseItems : ['55378008','531608', '376006'],
			operators : ['+', '*', '/'],
			keys : ['1','2','3','4','5','6','7','8','9','0','=','-'],
			debug : false
	});

};


