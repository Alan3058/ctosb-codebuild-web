/**
 * form util plugin
 * @param $
 * @param undefined
 */
(function($,undefined){
	$.fn.extend({
		/**
		 * 填充表单
		 */
		fill: function(data) {
			self = this;
			data = $.extend({}, data);
			return this.each(function() {
				//遍历json数据，并填充数据
				$.each(data, function(k, v) {
					var selector, ele;
					selector = '[name="'+k+'"]';
					ele = self.find(selector);
					ele.each(function(){
						var $this = $(this);
						if(($this.is("input") && ($this.attr("type") == "checkbox" || $this.attr("type") == "radio")) || $this.is("select")){
							if(!$.isArray(v)){
								$this.val([v]);
							}else{
								$this.val(v);
							}
						}else{
							$this.val(v);
						}
					});
				});
			});
		},
		/**
		 * 序列化表单成对象
		 */
		serializeObject: function() {
			var obj = {};
			var arr = this.serializeArray();
			$.each(arr,function(){
				if(obj[this.name]!== undefined){
					if(!$.isArray(obj[this.name])){
						obj[this.name]=[obj[this.name]];
					}
					obj[this.name].push(this.value);
				}else{
					obj[this.name]=this.value;
				}
			});
			return obj;
		}
	});
})(jQuery);