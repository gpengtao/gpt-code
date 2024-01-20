// ==UserScript==
// @name         删除右侧无用信息、增加展示上级job配置的dt条件
// @namespace    http://tampermonkey.net/
// @version      2024-01-19
// @description  try to take over the world!
// @author       pengtao.geng
// @match        https://schedule.corp.xxxxxxxxx.com/job/*
// @icon         data:image/gif;base64,R0lGODlhAQABAAAAACH5BAEKAAEALAAAAAABAAEAAAICTAEAOw==
// @require      https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js
// @grant        unsafeWindow
// ==/UserScript==

(function () {
    'use strict';

    // 获取job名字
    let jobName = $('h1').text().replace('Project ', '');
    console.log('job名字是:' + jobName);
    if (!jobName) {
        console.log('不是job展示页面无需处理');
        return
    }

    // 删除右侧无用信息,
    // 1、找到main
    let main1 = document.getElementById('main-panel');
    // 2、删除下面的序号5的div
    main1.getElementsByTagName('div')[5].remove();

    // 增加展示依赖的上级job配置的dt条件
    // 1、构造依赖map
    let jobDepMap = new Map();
    $.ajaxSettings.async = false;
    $.get("https://schedule.corp.xxxxxxxxx.com/job/" + jobName + "/configure")
        .done(
            function (response) {
                let dependencies = $(response).find("[name='t']");
                $(dependencies).each(function () {
                    let inputs = $(this).find("input");
                    // 序号0是job名字，序号1是条件
                    jobDepMap.set($($(inputs)[0]).val(), $($(inputs)[1]).val())
                });
            });
    console.log('构造的依赖map如下:');
    console.log(jobDepMap);
    // 2、增加展示dt条件
    let main = $('#main-panel');
    let ul = $(main).find('ul')
    let a = $(ul).find('a');
    $(a).each(function () {
        let dt = jobDepMap.get(this.innerText);
        if (dt) {// 这里取不到说明不是上级任务
            console.log('上级任务 ' + this.innerText + ' 增加展示dt: ' + dt);
            $(this).html(this.innerText + '【' + dt + '】');
        }
    })
})();