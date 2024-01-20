// ==UserScript==
// @name         删除 schedule job 页面右侧无用信息
// @namespace    http://tampermonkey.net/
// @version      2024-01-19
// @description  try to take over the world!
// @author       gpt
// @match        https://schedule.corp.xxx.com/job/*
// @icon         data:image/gif;base64,R0lGODlhAQABAAAAACH5BAEKAAEALAAAAAABAAEAAAICTAEAOw==
// @require      https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js
// @grant        none
// ==/UserScript==

(function() {
    // 'use strict';

    this.$ = this.jQuery = jQuery.noConflict(true);

    console.log($('#main-panel'))

    // 找到main
    let main = document.getElementById('main-panel');
    // 删除下面的序号5的div
    main.getElementsByTagName('div')[5].remove();
})();