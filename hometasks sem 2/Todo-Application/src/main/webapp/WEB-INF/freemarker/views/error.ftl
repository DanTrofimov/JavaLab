<#import "spring.ftl" as spring/>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Error</title>
</head>
<body
    style="height: 100vh; width: auto; text-align: center; display: grid; place-items: center; font-size: 20px; font-family: Consolas, sans-serif"
>
    <div>
        <#if showError??>
            <p>${ showError }</p>
        </#if>
        <#if exceptionInfo??>
            <p>${ exceptionInfo }</p>
        </#if>
        <a href='<@spring.url "/"/>'>back</a>
    </div>
</body>
</html>