/*
 AngularJS v1.2.18
 (c) 2010-2014 Google, Inc. http://angularjs.org
 License: MIT
*/
(function(u,f,P){'use strict';f.module("ngAnimate",["ng"]).factory("$$animateReflow",["$$rAF","$document",function(f,u){return function(e){return f(function(){e()})}}]).config(["$provide","$animateProvider",function(W,H){function e(f){for(var e=0;e<f.length;e++){var h=f[e];if(h.nodeType==aa)return h}}function C(h){return f.element(e(h))}var n=f.noop,h=f.forEach,Q=H.$$selectors,aa=1,k="$$ngAnimateState",K="ng-animate",g={running:!0};W.decorator("$animate",["$delegate","$injector","$sniffer","$rootElement",
"$$asyncCallback","$rootScope","$document",function(y,u,$,L,F,I,P){function R(a){if(a){var b=[],c={};a=a.substr(1).split(".");($.transitions||$.animations)&&b.push(u.get(Q[""]));for(var d=0;d<a.length;d++){var f=a[d],e=Q[f];e&&!c[f]&&(b.push(u.get(e)),c[f]=!0)}return b}}function M(a,b,c){function d(a,b){var c=a[b],d=a["before"+b.charAt(0).toUpperCase()+b.substr(1)];if(c||d)return"leave"==b&&(d=c,c=null),t.push({event:b,fn:c}),l.push({event:b,fn:d}),!0}function e(b,d,f){var q=[];h(b,function(a){a.fn&&
q.push(a)});var m=0;h(q,function(b,e){var h=function(){a:{if(d){(d[e]||n)();if(++m<q.length)break a;d=null}f()}};switch(b.event){case "setClass":d.push(b.fn(a,p,A,h));break;case "addClass":d.push(b.fn(a,p||c,h));break;case "removeClass":d.push(b.fn(a,A||c,h));break;default:d.push(b.fn(a,h))}});d&&0===d.length&&f()}var w=a[0];if(w){var k="setClass"==b,g=k||"addClass"==b||"removeClass"==b,p,A;f.isArray(c)&&(p=c[0],A=c[1],c=p+" "+A);var B=a.attr("class")+" "+c;if(T(B)){var r=n,v=[],l=[],x=n,m=[],t=[],
B=(" "+B).replace(/\s+/g,".");h(R(B),function(a){!d(a,b)&&k&&(d(a,"addClass"),d(a,"removeClass"))});return{node:w,event:b,className:c,isClassBased:g,isSetClassOperation:k,before:function(a){r=a;e(l,v,function(){r=n;a()})},after:function(a){x=a;e(t,m,function(){x=n;a()})},cancel:function(){v&&(h(v,function(a){(a||n)(!0)}),r(!0));m&&(h(m,function(a){(a||n)(!0)}),x(!0))}}}}}function z(a,b,c,d,e,w,g){function n(d){var e="$animate:"+d;x&&(x[e]&&0<x[e].length)&&F(function(){c.triggerHandler(e,{event:a,
className:b})})}function p(){n("before")}function A(){n("after")}function B(){n("close");g&&F(function(){g()})}function r(){r.hasBeenRun||(r.hasBeenRun=!0,w())}function v(){if(!v.hasBeenRun){v.hasBeenRun=!0;var d=c.data(k);d&&(l&&l.isClassBased?D(c,b):(F(function(){var d=c.data(k)||{};z==d.index&&D(c,b,a)}),c.data(k,d)));B()}}var l=M(c,a,b);if(l){b=l.className;var x=f.element._data(l.node),x=x&&x.events;d||(d=e?e.parent():c.parent());var m=c.data(k)||{};e=m.active||{};var t=m.totalActive||0,u=m.last;
if(l.isClassBased&&(m.disabled||u&&!u.isClassBased)||N(c,d))r(),p(),A(),v();else{d=!1;if(0<t){m=[];if(l.isClassBased)"setClass"==u.event?(m.push(u),D(c,b)):e[b]&&(y=e[b],y.event==a?d=!0:(m.push(y),D(c,b)));else if("leave"==a&&e["ng-leave"])d=!0;else{for(var y in e)m.push(e[y]),D(c,y);e={};t=0}0<m.length&&h(m,function(a){a.cancel()})}!l.isClassBased||(l.isSetClassOperation||d)||(d="addClass"==a==c.hasClass(b));if(d)r(),p(),A(),B();else{if("leave"==a)c.one("$destroy",function(a){a=f.element(this);var b=
a.data(k);b&&(b=b.active["ng-leave"])&&(b.cancel(),D(a,"ng-leave"))});c.addClass(K);var z=O++;t++;e[b]=l;c.data(k,{last:l,active:e,index:z,totalActive:t});p();l.before(function(d){var e=c.data(k);d=d||!e||!e.active[b]||l.isClassBased&&e.active[b].event!=a;r();!0===d?v():(A(),l.after(v))})}}}else r(),p(),A(),v()}function U(a){if(a=e(a))a=f.isFunction(a.getElementsByClassName)?a.getElementsByClassName(K):a.querySelectorAll("."+K),h(a,function(a){a=f.element(a);(a=a.data(k))&&a.active&&h(a.active,function(a){a.cancel()})})}
function D(a,b){if(e(a)==e(L))g.disabled||(g.running=!1,g.structural=!1);else if(b){var c=a.data(k)||{},d=!0===b;!d&&(c.active&&c.active[b])&&(c.totalActive--,delete c.active[b]);if(d||!c.totalActive)a.removeClass(K),a.removeData(k)}}function N(a,b){if(g.disabled)return!0;if(e(a)==e(L))return g.disabled||g.running;do{if(0===b.length)break;var c=e(b)==e(L),d=c?g:b.data(k),d=d&&(!!d.disabled||d.running||0<d.totalActive);if(c||d)return d;if(c)break}while(b=b.parent());return!0}var O=0;L.data(k,g);I.$$postDigest(function(){I.$$postDigest(function(){g.running=
!1})});var V=H.classNameFilter(),T=V?function(a){return V.test(a)}:function(){return!0};return{enter:function(a,b,c,d){a=f.element(a);b=b&&f.element(b);c=c&&f.element(c);this.enabled(!1,a);y.enter(a,b,c);I.$$postDigest(function(){a=C(a);z("enter","ng-enter",a,b,c,n,d)})},leave:function(a,b){a=f.element(a);U(a);this.enabled(!1,a);I.$$postDigest(function(){z("leave","ng-leave",C(a),null,null,function(){y.leave(a)},b)})},move:function(a,b,c,d){a=f.element(a);b=b&&f.element(b);c=c&&f.element(c);U(a);
this.enabled(!1,a);y.move(a,b,c);I.$$postDigest(function(){a=C(a);z("move","ng-move",a,b,c,n,d)})},addClass:function(a,b,c){a=f.element(a);a=C(a);z("addClass",b,a,null,null,function(){y.addClass(a,b)},c)},removeClass:function(a,b,c){a=f.element(a);a=C(a);z("removeClass",b,a,null,null,function(){y.removeClass(a,b)},c)},setClass:function(a,b,c,d){a=f.element(a);a=C(a);z("setClass",[b,c],a,null,null,function(){y.setClass(a,b,c)},d)},enabled:function(a,b){switch(arguments.length){case 2:if(a)D(b);else{var c=
b.data(k)||{};c.disabled=!0;b.data(k,c)}break;case 1:g.disabled=!a;break;default:a=!g.disabled}return!!a}}}]);H.register("",["$window","$sniffer","$timeout","$$animateReflow",function(k,g,C,L){function F(a,E){S&&S();X.push(E);S=L(function(){h(X,function(a){a()});X=[];S=null;q={}})}function I(a,E){var b=e(a);a=f.element(b);Y.push(a);b=Date.now()+E;b<=ea||(C.cancel(da),ea=b,da=C(function(){K(Y);Y=[]},E,!1))}function K(a){h(a,function(a){(a=a.data(m))&&(a.closeAnimationFn||n)()})}function R(a,E){var b=
E?q[E]:null;if(!b){var c=0,d=0,e=0,f=0,m,Z,s,g;h(a,function(a){if(a.nodeType==aa){a=k.getComputedStyle(a)||{};s=a[J+B];c=Math.max(M(s),c);g=a[J+r];m=a[J+v];d=Math.max(M(m),d);Z=a[p+v];f=Math.max(M(Z),f);var b=M(a[p+B]);0<b&&(b*=parseInt(a[p+l],10)||1);e=Math.max(b,e)}});b={total:0,transitionPropertyStyle:g,transitionDurationStyle:s,transitionDelayStyle:m,transitionDelay:d,transitionDuration:c,animationDelayStyle:Z,animationDelay:f,animationDuration:e};E&&(q[E]=b)}return b}function M(a){var b=0;a=
f.isString(a)?a.split(/\s*,\s*/):[];h(a,function(a){b=Math.max(parseFloat(a)||0,b)});return b}function z(a){var b=a.parent(),c=b.data(x);c||(b.data(x,++ca),c=ca);return c+"-"+e(a).getAttribute("class")}function U(a,b,c,d){var f=z(b),h=f+" "+c,k=q[h]?++q[h].total:0,g={};if(0<k){var l=c+"-stagger",g=f+" "+l;(f=!q[g])&&b.addClass(l);g=R(b,g);f&&b.removeClass(l)}d=d||function(a){return a()};b.addClass(c);var l=b.data(m)||{},s=d(function(){return R(b,h)});d=s.transitionDuration;f=s.animationDuration;if(0===
d&&0===f)return b.removeClass(c),!1;b.data(m,{running:l.running||0,itemIndex:k,stagger:g,timings:s,closeAnimationFn:n});a=0<l.running||"setClass"==a;0<d&&D(b,c,a);0<f&&(0<g.animationDelay&&0===g.animationDuration)&&(e(b).style[p]="none 0s");return!0}function D(a,b,c){"ng-enter"!=b&&("ng-move"!=b&&"ng-leave"!=b)&&c?a.addClass(t):e(a).style[J+r]="none"}function N(a,b){var c=J+r,d=e(a);d.style[c]&&0<d.style[c].length&&(d.style[c]="");a.removeClass(t)}function O(a){var b=p;a=e(a);a.style[b]&&0<a.style[b].length&&
(a.style[b]="")}function V(a,b,c,f){function g(a){b.off(z,l);b.removeClass(p);d(b,c);a=e(b);for(var fa in t)a.style.removeProperty(t[fa])}function l(a){a.stopPropagation();var b=a.originalEvent||a;a=b.$manualTimeStamp||b.timeStamp||Date.now();b=parseFloat(b.elapsedTime.toFixed(Q));Math.max(a-y,0)>=x&&b>=u&&f()}var k=e(b);a=b.data(m);if(-1!=k.getAttribute("class").indexOf(c)&&a){var p="";h(c.split(" "),function(a,b){p+=(0<b?" ":"")+a+"-active"});var n=a.stagger,s=a.timings,r=a.itemIndex,u=Math.max(s.transitionDuration,
s.animationDuration),v=Math.max(s.transitionDelay,s.animationDelay),x=v*ba,y=Date.now(),z=A+" "+H,q="",t=[];if(0<s.transitionDuration){var B=s.transitionPropertyStyle;-1==B.indexOf("all")&&(q+=w+"transition-property: "+B+";",q+=w+"transition-duration: "+s.transitionDurationStyle+";",t.push(w+"transition-property"),t.push(w+"transition-duration"))}0<r&&(0<n.transitionDelay&&0===n.transitionDuration&&(q+=w+"transition-delay: "+T(s.transitionDelayStyle,n.transitionDelay,r)+"; ",t.push(w+"transition-delay")),
0<n.animationDelay&&0===n.animationDuration&&(q+=w+"animation-delay: "+T(s.animationDelayStyle,n.animationDelay,r)+"; ",t.push(w+"animation-delay")));0<t.length&&(s=k.getAttribute("style")||"",k.setAttribute("style",s+"; "+q));b.on(z,l);b.addClass(p);a.closeAnimationFn=function(){g();f()};k=(r*(Math.max(n.animationDelay,n.transitionDelay)||0)+(v+u)*W)*ba;a.running++;I(b,k);return g}f()}function T(a,b,c){var d="";h(a.split(","),function(a,e){d+=(0<e?",":"")+(c*b+parseInt(a,10))+"s"});return d}function a(a,
b,c,e){if(U(a,b,c,e))return function(a){a&&d(b,c)}}function b(a,b,c,e){if(b.data(m))return V(a,b,c,e);d(b,c);e()}function c(c,d,e,f){var g=a(c,d,e);if(g){var h=g;F(d,function(){N(d,e);O(d);h=b(c,d,e,f)});return function(a){(h||n)(a)}}f()}function d(a,b){a.removeClass(b);var c=a.data(m);c&&(c.running&&c.running--,c.running&&0!==c.running||a.removeData(m))}function G(a,b){var c="";a=f.isArray(a)?a:a.split(/\s+/);h(a,function(a,d){a&&0<a.length&&(c+=(0<d?" ":"")+a+b)});return c}var w="",J,H,p,A;u.ontransitionend===
P&&u.onwebkittransitionend!==P?(w="-webkit-",J="WebkitTransition",H="webkitTransitionEnd transitionend"):(J="transition",H="transitionend");u.onanimationend===P&&u.onwebkitanimationend!==P?(w="-webkit-",p="WebkitAnimation",A="webkitAnimationEnd animationend"):(p="animation",A="animationend");var B="Duration",r="Property",v="Delay",l="IterationCount",x="$$ngAnimateKey",m="$$ngAnimateCSS3Data",t="ng-animate-block-transitions",Q=3,W=1.5,ba=1E3,q={},ca=0,X=[],S,da=null,ea=0,Y=[];return{enter:function(a,
b){return c("enter",a,"ng-enter",b)},leave:function(a,b){return c("leave",a,"ng-leave",b)},move:function(a,b){return c("move",a,"ng-move",b)},beforeSetClass:function(b,c,d,e){var f=G(d,"-remove")+" "+G(c,"-add"),g=a("setClass",b,f,function(a){var e=b.attr("class");b.removeClass(d);b.addClass(c);a=a();b.attr("class",e);return a});if(g)return F(b,function(){N(b,f);O(b);e()}),g;e()},beforeAddClass:function(b,c,d){var e=a("addClass",b,G(c,"-add"),function(a){b.addClass(c);a=a();b.removeClass(c);return a});
if(e)return F(b,function(){N(b,c);O(b);d()}),e;d()},setClass:function(a,c,d,e){d=G(d,"-remove");c=G(c,"-add");return b("setClass",a,d+" "+c,e)},addClass:function(a,c,d){return b("addClass",a,G(c,"-add"),d)},beforeRemoveClass:function(b,c,d){var e=a("removeClass",b,G(c,"-remove"),function(a){var d=b.attr("class");b.removeClass(c);a=a();b.attr("class",d);return a});if(e)return F(b,function(){N(b,c);O(b);d()}),e;d()},removeClass:function(a,c,d){return b("removeClass",a,G(c,"-remove"),d)}}}])}])})(window,
window.angular);
//# sourceMappingURL=angular-animate.min.js.map
