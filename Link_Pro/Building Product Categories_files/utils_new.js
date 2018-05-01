/*global document, window, alert, $, set_cookie, get_cookie */
//--------------------------------------------------
//
//    JS UTIL ROUTINES
//
//--------------------------------------------------
function get_param(param_name) {
    'use strict';
    var str = "";
    var str2 = window.location.href;
    var i = 0;
    var j = 0;
    i = str2.indexOf("" + param_name + "=");
    if (i > -1) {
        j = str2.indexOf("&", param_name.length + 1 + i);
        if (j === -1) { j = str2.length; }
        str = str2.substring(i + param_name.length + 1, j);
    }
    return str;
}

function addPluginSearchEngine(searchenginetype) {
    'use strict';
    // Only IE7+ and Firefox 2+ Currently Officially Supported
    
    var searchpluginURL = 'http://www.arcat.com/searchplugins/';
    var searchplugin = 'INVALID';

    if (searchenginetype === 'bim') {
        searchplugin = "arcatsearchbim.xml";
    }
    else if (searchenginetype === 'specs') {
        searchplugin = "arcatsearchspecs.xml";
    }
    else if (searchenginetype === 'product') {
        searchplugin = "arcatsearchproduct.xml";
    }
    else if (searchenginetype === 'company') {
        searchplugin = "arcatsearchcompany.xml";
    }
    else {
        searchenginetype = 'INVALID';
    }
    
    if (searchenginetype !== 'INVALID' && searchplugin !== 'INVALID') {
        if (window.external && ("AddSearchProvider" in window.external)) {
            // Firefox 2+ and IE 7+, Other potentially Compatible Browsers
            window.external.AddSearchProvider(searchpluginURL + searchplugin);
        }
        else if (window.sidebar && ("addSearchEngine" in window.sidebar)) {
            // Firefox <= 1.5, Sherlock Compatible Browsers
            alert("No Search Engine Plugin Support for your Browser.  Users of Firefox 1.5 and earlier should upgrade to the latest version");
        }
        else {
            // No search engine support (IE 6, Opera, Safari, etc).
            alert("No Search Engine Plugin Support for your Browser.");
        }
    }
    else {
        alert("Error: Search Engine Plugin Not Found");
    }
}

//--------------------------------------------------
function validateEmail(email) {
    'use strict';
    var re = /\S+@\S+\.\S+/;
    // var re = /\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}\b./;
    return re.test(email);
}

function submitComment() {
    'use strict';
    var form = document.getElementById('commentForm');

    var els = form.elements;
    var feedback_comment = els.feedback_comment.value;
    var type = els.type.value;
    var action = els.action.value;
    var feedback_name = els.feedback_name.value;
    var feedback_email = els.feedback_email.value;
    var feedback_details = els.feedback_details.value;
    var coid = els.coid.value;
    var company = els.company.value;
    var x = els.x.value;
    var y = els.y.value;

    var msg = [];
    if (!feedback_name) {
        msg.push('Name is required.');
    }
    if (!feedback_email) {
        msg.push('Email is required.');
    } else if (!validateEmail(feedback_email)) {
        msg.push('Email is not valid.');
    }
    if (!feedback_details) {
        msg.push('A comment is required.');
    }
    if (msg.length > 0) {
        var out = msg.join('\n');
        alert(out);
        return;
    }

    if (type === '2') {
        action = 'SendFeedback';
    } else {
        action = 'UserEmail';
    }

    var data = {
        'feedback_comment': feedback_comment,
        'type': type,
        'action': action,
        'feedback_name': feedback_name,
        'feedback_email': feedback_email,
        'feedback_details': feedback_details,
        'coid': coid,
        'company': company,
        'x': x,
        'y': y
    };

    set_cookie("USER_NAME", feedback_name, 400);
    set_cookie("USER_EMAIL", feedback_email, 400);

    var submittedDiv = document.getElementById('commentFormSubmitted'),
        submittedManufacturerDiv = document.getElementById('commentFormSubmittedManufacturer'),
        submittedBodyDiv = document.getElementById('commentFormBody'),
        submitBtn = document.getElementById('commentFormSubmit');
        // closeBtn = document.getElementById('commentFormClose');

    var url = '/users.pl';

    $.ajax({
        type: 'POST',
        url: url,
        data: data
    }).done(function () {
        $(submittedBodyDiv).addClass('hidden');
        $(submitBtn).addClass('hidden');
        if (type === '1') {
            $(submittedManufacturerDiv).removeClass('hidden');
        } else {
            $(submittedDiv).removeClass('hidden');
        }

    }).fail(function () {
        alert("Your submission failed. Please try again.");
        // $(form).addClass('hidden');
        // $(submitBtn).addClass('hidden');
        // $(submittedDiv).removeClass('hidden');
    }).always(function () {
//        alert("complete");
    });
}

function resetCommentForm() {
    'use strict';
    var form = document.getElementById('commentForm'),
        submittedDiv = document.getElementById('commentFormSubmitted'),
        submittedManufacturerDiv = document.getElementById('commentFormSubmittedManufacturer'),
        submittedBodyDiv = document.getElementById('commentFormBody'),
        submitBtn = document.getElementById('commentFormSubmit');

    $(form).removeClass('hidden');
    $(submittedBodyDiv).removeClass('hidden');
    $(submitBtn).removeClass('hidden');
    $(submittedDiv).addClass('hidden');
    $(submittedManufacturerDiv).addClass('hidden');

    var els = form.elements;
    els.feedback_name.value = '';
    els.feedback_email.value = '';
    els.feedback_details.value = '';
    els.coid.value = '';
    els.company.value = '';
}

function openEmailModal(label, msgPrefix, companyId, msgType, companyName) {
    'use strict';
    var form = document.getElementById('commentForm'),
        $label = $('#inputCommentLabel strong');

    var els = form.elements,
        feedback_details = els.feedback_details,
        feedback_name = els.feedback_name,
        feedback_email = els.feedback_email,
        coid = els.coid,
        company = els.company,
        type = els.type;

    var user_name = get_cookie("USER_NAME"),
        user_email = get_cookie("USER_EMAIL");
        
    if (user_name) {
        feedback_name.value = user_name;
    }
    if (user_email) {
        feedback_email.value = user_email;
    }


    // Use params or defaults
    $label.text(label || 'Comments');
    feedback_details.value = msgPrefix || '';
    coid.value = companyId || '';
    company.value = companyName || '';

    var $hdrText = $('#commentFormBody > p', form);
    if (msgType) {
        type.value = msgType;
    } else {
        type.value = 2;
    }

    // Company type
    if (type.value === '1') {
        var companyText;
        if (companyName) {
            var cleanName = decodeURIComponent(companyName).replace(/\+/g, ' ');
            companyText = ' in ' + cleanName;
        } else {
            companyText = '';
        }
        var joinText = ['Thanks for your interest', companyText,
            '. Please use this form if you have any questions or comments.'].join('');
        $hdrText.html(joinText);

    } else {
        $hdrText.text('Thanks for your interest in Arcat.' +
            ' Please use this form' +
            ' if you have any questions or comments about our website' +
            ' and we\'ll get back with you very soon.');
    }

    $('#commentModal').modal('show');
}

// PROFILE REQUEST

function submitProfileRequest() {
    'use strict';
    var form = document.getElementById('profileForm');

    var now = new Date(),
        year = now.getFullYear(),
        month = String(now.getMonth() + 1),
        day = String(now.getDate());

    if (month.length === 1) {
        month = '0' + month;
    }
    if (day.length === 1) {
        day = '0' + day;
    }
    var dateValue = [year, month, day].join('-');

    var config = window.companyProfileConfig || {};

    var els = form.elements;

    var data = {
        date: dateValue,
        company: config.companyName || els.company.value || '',
        coid: config.coid || els.coid.value || '',
        rep: config.rep || els.rep.value || '',
        userid: '',
        submit_try: els.submit_try.value,
        project_seq: els.project_seq.value,
        action: els.action.value,
        firstname: els.firstname.value,
        lastname: els.lastname.value,
        coname: els.coname.value,
        address1: els.address1.value,
        city: els.city.value,
        st: els.st.value,
        zip: els.zip.value,
        phone: els.phone.value,
        email: els.email.value,
        usertype: els.usertype.value,
        project_name: els.project_name.value,
        project_type: els.project_type.value,
        message: els.message.value
    };

    var msg = [];
    if (!data.firstname) {
        msg.push('A first name is required.');
    }
    if (!data.lastname) {
        msg.push('A last name is required.');
    }
    if (!data.coname) {
        msg.push('A company name is required.');
    }
    if (!data.address1) {
        msg.push('An address is required.');
    }
    if (!data.city) {
        msg.push('A city is required.');
    }
    if (!data.st) {
        msg.push('A state is required.');
    }
    if (!data.zip) {
        msg.push('A zip is required.');
    }
    if (!data.phone) {
        msg.push('A phone is required.');
    }
    if (!data.email) {
        msg.push('An email is required.');
    } else if (!validateEmail(data.email)) {
        msg.push('Email is not valid.');
    }
    if (!data.usertype) {
        msg.push('A job type is required.');
    }
    if (!data.project_name) {
        msg.push('A project name is required.');
    }
    if (!data.project_type) {
        msg.push('A project type is required.');
    }
    if (!data.message) {
        msg.push('A message is required.');
    }
    if (msg.length > 0) {
        var out = msg.join('\n');
        alert(out);
        return;
    }

    set_cookie("USER_FIRST_NAME", data.firstname, 400);
    set_cookie("USER_LAST_NAME", data.lastname, 400);
    set_cookie("USER_EMAIL", data.email, 400);

    var submittedManufacturerDiv = document.getElementById('profileFormSubmittedManufacturer'),
        submittedBodyDiv = document.getElementById('profileFormBody'),
        submitBtn = document.getElementById('profileFormSubmit');
    // closeBtn = document.getElementById('profileFormClose');

    var url = '/users.pl';

    $.ajax({
        type: 'POST',
        url: url,
        data: data
    }).done(function () {
        $(submittedBodyDiv).addClass('hidden');
        $(submitBtn).addClass('hidden');
        $(submittedManufacturerDiv).removeClass('hidden');

    }).fail(function () {
        alert("Your submission failed. Please try again.");
        // $(form).addClass('hidden');
        // $(submitBtn).addClass('hidden');
        // $(submittedDiv).removeClass('hidden');
    }).always(function () {
//        alert("complete");
    });
}

function resetProfileRequestForm() {
    'use strict';
    var form = document.getElementById('profileForm'),
        submittedDiv = document.getElementById('profileFormSubmitted'),
        submittedManufacturerDiv = document.getElementById('profileFormSubmittedManufacturer'),
        submittedBodyDiv = document.getElementById('profileFormBody'),
        submitBtn = document.getElementById('profileFormSubmit');

    $(form).removeClass('hidden');
    $(submittedBodyDiv).removeClass('hidden');
    $(submitBtn).removeClass('hidden');
    $(submittedDiv).addClass('hidden');
    $(submittedManufacturerDiv).addClass('hidden');


    var els = form.elements;
    els.firstname.value = '';
    els.lastname.value = '';
    els.coname.value = '';
    els.address1.value = '';
    els.city.value = '';
    els.st.value = '';
    els.zip.value = '';
    els.phone.value = '';
    els.email.value = '';
    els.usertype.value = '';
    els.project_name.value = '';
    els.project_type.value = '';
    els.message.value = '';
    //console.log(form.elements);
}

function openProfileRequestModal(msgPrefix, interest) {
    'use strict';
    var form = document.getElementById('profileForm');

    var els = form.elements,
        message = els.message,
        firstname = els.firstname,
        lastname = els.lastname,
        email = els.email;

    var user_firstname = get_cookie("USER_FIRST_NAME"),
        user_lastname = get_cookie("USER_LAST_NAME"),
        user_email = get_cookie("USER_EMAIL");

    if (user_firstname) {
        firstname.value = user_firstname;
    }
    if (user_lastname) {
        lastname.value = user_lastname;
    }
    if (user_email) {
        email.value = user_email;
    }

    // Use params or defaults
    message.value = msgPrefix || '';

    var $hdrText = $('#profileFormBody > p', form);

    // Company type
    var interestText;
    if (interest) {
        var cleanName = decodeURIComponent(interest).replace(/\+/g, ' ');
        interestText = ' in ' + cleanName;
    } else {
        interestText = '';
    }
    var joinText = ['Thanks for your interest', interestText,
        '. Please use this form if you have any questions or comments.'].join('');
    $hdrText.html(joinText);


    $('#profileModal').modal('show');
}


function showMfSwitchModal() {
	//	BJ REMOVED - 2014.08.28 - IT HAS BEEN UP LONG ENOUGH
    //'use strict';
    //var key = 'MF_MODAL_SEEN',
    //    isSeen = get_cookie(key);

    //if (!isSeen) {
    //    var $modal = $(document.getElementById('mfSwitchModal'));
    //    $modal.modal('show');
    //    set_cookie(key, "true", 4000);
    //}
}

function initCookies(mf) {
  'use strict';
  // Should this default to MF95 still?
  var cookie_mf = mf || 'MF95';
  set_cookie('mf', cookie_mf, 10000);

//  var qstr = get_param('qstr');
//  var inqstr = get_param('inqstr');
//  var qtype = get_param('qtype');

  var banner_displayed = 0;

  function formHandler2() {
    var URL = document.form2.select2.options[document.form2.select2.selectedIndex].value;
    window.location.href = URL;
  }

  var user_id = get_cookie('USER_ID');
  if (!user_id) {
    user_id = "" + new Date() + "" + (Math.round(Math.random()*5000000));
    set_cookie("USER_ID",user_id, 400);
  }

}

(function() {
  var method;
  var noop = function () {};
  var methods = [
    'assert', 'clear', 'count', 'debug', 'dir', 'dirxml', 'error',
    'exception', 'group', 'groupCollapsed', 'groupEnd', 'info', 'log',
    'markTimeline', 'profile', 'profileEnd', 'table', 'time', 'timeEnd',
    'timeStamp', 'trace', 'warn'
  ];
  var length = methods.length;
  var console = (window.console = window.console || {});

  while (length--) {
    method = methods[length];

    // Only stub undefined methods.
    if (!console[method]) {
      console[method] = noop;
    }
  }

  /*IE8 toISOString hack */
  if (!Date.prototype.toISOString) {
    Date.prototype.toISOString = function() {
      function pad(n) { return n < 10 ? '0' + n : n }
      return this.getUTCFullYear() + '-'
        + pad(this.getUTCMonth() + 1) + '-'
        + pad(this.getUTCDate()) + 'T'
        + pad(this.getUTCHours()) + ':'
        + pad(this.getUTCMinutes()) + ':'
        + pad(this.getUTCSeconds()) + '.'
        + pad(this.getUTCMilliseconds()) + 'Z';
    };
  }

  /*IE8 hack to support forEach */
  if (!Array.prototype.forEach) {
    Array.prototype.forEach = function(fn, scope) {
      for(var i = 0, len = this.length; i < len; ++i) {
        fn.call(scope, this[i], i, this);
      }
    }
  }
}());
