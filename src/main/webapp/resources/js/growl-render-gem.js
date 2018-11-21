PrimeFaces.widget.Growl = PrimeFaces.widget.BaseWidget
		.extend({
			init : function(a) {
				this.cfg = a;
				this.id = this.cfg.id;
				this.jqId = PrimeFaces.escapeClientId(this.id);
				this.render();
				this.removeScriptElement(this.id)
			},
			refresh : function(a) {
				this.cfg = a;
				this.show(a.msgs);
				this.removeScriptElement(this.id)
			},
			show : function(b) {
				var a = this;
				this.jq.css("z-index", ++PrimeFaces.zindex);
				this.removeAll();
				$.each(b, function(c, d) {
					a.renderMessage(d)
				})
			},
			removeAll : function() {
				this.jq.children("div.ui-growl-item-container").remove()
			},
			render : function() {
				this.jq = $('<div id="' + this.id
						+ '_container" class="ui-growl ui-widget"></div>');
				this.jq.appendTo($(document.body));
				this.show(this.cfg.msgs)
			},
			renderMessage : function(e) {
				
				if(e.severity=='error'){
					var a = '<div class="ui-growl-item-container ui-state-highlight ui-corner-all ui-helper-hidden ui-shadow" aria-live="polite">';
					if ($('.gemerror').length == 0)
					$( ".ui-growl" ).wrap( "<div class='gemerror'></div>" );				
				
				
				}
				else{
					var a = '<div class="ui-growl-item-container ui-state-highlight ui-corner-all ui-helper-hidden ui-shadow" aria-live="polite">';		
				}
				a += '<div class="ui-growl-item">';
				a += '<div class="ui-growl-icon-close ui-icon ui-icon-closethick" style="display:none"></div>';
				a += '<span class="ui-growl-image ui-growl-image-' + e.severity
						+ '" />';
				a += '<div class="ui-growl-message">';
				a += '<span class="ui-growl-title"></span>';
				a += "<p></p>";
				a += '</div><div style="clear: both;"></div></div></div>';
				var c = $(a), b = c.find("span.ui-growl-title"), d = b.next();
				if (this.cfg.escape) {
					b.text(e.summary);
					d.text(e.detail)
				} else {
					b.html(e.summary);
					d.html(e.detail)
				}
				this.bindEvents(c);
				c.appendTo(this.jq).fadeIn()
			},
			bindEvents : function(b) {
				var a = this, c = this.cfg.sticky;
				b.mouseover(function() {
					var d = $(this);
					if (!d.is(":animated")) {
						d.find("div.ui-growl-icon-close:first").show()
					}
				}).mouseout(function() {
					$(this).find("div.ui-growl-icon-close:first").hide()
				});
				b.find("div.ui-growl-icon-close").click(function() {
					a.removeMessage(b);
					
					if ($('.ui-growl-item-container').length == 0)
					$( ".ui-growl" ).unwrap( "<div class='gemerror'></div>" );
					if (!c) {
						clearTimeout(b.data("timeout"))
					}
				});
				if (!c) {
					this.setRemovalTimeout(b)
				}
			},
			removeMessage : function(a) {
				//a.fadeTo("normal", 0, function() {
				//	a.slideUp("normal", "easeInOutCirc", function() {
						a.remove()
					//})
				//})
			},
			setRemovalTimeout : function(b) {
				var a = this;
				var c = setTimeout(function() {
					a.removeMessage(b)
				}, this.cfg.life);
				b.data("timeout", c)
			}
		});
