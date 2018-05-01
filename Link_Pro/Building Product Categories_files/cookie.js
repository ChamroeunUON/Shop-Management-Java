/*global window, document, escape, unescape */
//-------------------------------------------------------------
//
//	COOKIE FUNCTIONS
//
//-------------------------------------------------------------
function get_cookie(NameOfCookie) {
    'use strict';
    if (document.cookie.length > 0) {
        var begin = document.cookie.indexOf(NameOfCookie + "=");
        if (begin !== -1) {
            begin += NameOfCookie.length + 1;
            var end = document.cookie.indexOf(";", begin);
            if (end === -1) {
                end = document.cookie.length;
            }
            return unescape(document.cookie.substring(begin, end));
        }
    }
    return null;
}

function del_cookie(NameOfCookie, domain) {
    'use strict';
    if (get_cookie(NameOfCookie) || domain) {
        document.cookie = NameOfCookie + "=" + "; expires=Thu, 01-Jan-70 00:00:01 GMT; " + domain;
    }
}

function set_cookie(NameOfCookie, value, expiredays) {
    'use strict';
    var domain = window.location.hostname,
        cookie_domain = ['domain=', domain, ';'].join(''),
        old_cookie_domain = ['domain=.', domain, ';'].join('');
    del_cookie(NameOfCookie, cookie_domain);
    del_cookie(NameOfCookie, old_cookie_domain);
    var extra_domain;
    if (domain === 'www.arcat.com' || domain === 'dev.www.arcat.com') {
      extra_domain = 'domain=.arcat.com;';
      del_cookie(NameOfCookie, extra_domain);
    }
    var expires;
    if (expiredays) {
        var ExpireDate = new Date();
        ExpireDate.setTime(ExpireDate.getTime() + (expiredays * 24 * 3600 * 1000));
        expires = [' expires=', ExpireDate.toGMTString(), ';'].join('');
    } else {
        expires = '';
    }
    var cookieval = [NameOfCookie, '=', escape(value), ';', ' path=/;',
        expires, cookie_domain].join('');
    document.cookie = cookieval;
}

function set_mf(mf) {
    'use strict';
    if (mf === '95') {
        mf = "MF95";
    } else if (mf === '04') {
        mf = "MF04";
    }
    set_cookie('mf', mf, 10000);
    if (!window.pageMFurl) {
        var pathname = document.location.pathname;
        if ('/' === pathname || '/index-bootstrap.shtml' === pathname ||
                '/index.html' === pathname) {
            window.pageMFurl = '/index04.html';
        } else if ('/index04.html' === pathname) {
            window.pageMFurl = '/index.html';
        }
    }
}

function redirect_via_mf() {
    'use strict';
    var mf = get_cookie('mf');
    //alert(mf);
    if (mf) {
        var pathname = document.location.pathname;
        if ('/' === pathname || '/index-bootstrap.shtml' === pathname || '/index.html' === pathname || '/index.html?mfrd=1' === pathname) {
            if (mf === '95' || mf === '04') {
                set_mf('MF04');
            }
            if (mf === 'MF95') {
                document.location.href = '/index95.html';
            }
        // } else if ('/index.html' === pathname ||
            // '/index04.html' === pathname ||
            // '/index95.html' === pathname) {
            // Do nothing
        }
    } else {
        set_mf('MF04');
        //document.location.href = '/index.html?mfrd=1';
    }
}

function switchMf () {

  var mf = get_cookie('mf');
  // use 04 as a default
  if (!mf) {
    mf = 'MF04';
  }
  if (mf === 'MF04') {
    mf = 'MF95';
  } else {
    mf = 'MF04';
  }
  set_cookie('mf', mf, 10000);
  window.location.href = pageMFurl
}


//-------------------------------------------------------------
