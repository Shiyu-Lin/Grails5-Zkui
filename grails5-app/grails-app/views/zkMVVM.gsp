<%--
  Created by IntelliJ IDEA.
  User: slin81
  Date: 2023-03-21
  Time: 10:19 a.m.
--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
 "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>ZK MVVM testing</title>
    <z:resources/>
</head>

<body>

%{--    Feature: ZK's MVVM features--}%
<z:window apply="org.zkoss.bind.BindComposer" viewModel="@id('vm') @init('zkMvvmViewModel')" validationMessages="@id('vmsgs')">

%{--    Feature: validator--}%
    <z:intbox value="@bind(vm.index) @validator(vm.rangeValidator)"/>
%{--    Feature: accessing validation messages--}%
    <z:label id="l1" value="@bind(vmsgs['intBox1'])"/>
    <z:doublebox value="@bind(vm.price)"/>
%{--    Feature: command binding--}%
    <z:toolbar>
        <z:button label="+index" onClick="@command('incrementIndexByTen')" />
        <z:button label="-price" onClick="@command('decrementPriceByFive')" />
    </z:toolbar>

    <z:div>
        <z:label value="ViewModel yea!!!"/>
    </z:div>


%{--    Feature: ZK 9+ compiles the pagination buttons differently (with html's accessibility attributes)--}%
    <z:grid width="300px" mold="paging" pageSize="4">
        <g:render template="someTags"/>
        <z:columns>
            <z:column label="Left"/>
            <z:column label="Right"/>
        </z:columns>
        <z:rows>
            <z:row>
                <z:label value="Item 1.1"/><z:label value="Item 1.2"/>
            </z:row>
            <z:row>
                <z:label value="Item 2.1"/><z:label value="Item 2.2"/>
            </z:row>
            <z:row>
                <z:label value="Item 3.1"/><z:label value="Item 3.2"/>
            </z:row>
            <z:row>
                <z:label value="Item 4.1"/><z:label value="Item 4.2"/>
            </z:row>
            <z:row>
                <z:label value="Item 5.1"/><z:label value="Item 5.2"/>
            </z:row>
            <z:row>
                <z:label value="Item 6.1"/><z:label value="Item 6.2"/>
            </z:row>
            <z:row>
                <z:label value="Item 7.1"/><z:label value="Item 7.2"/>
            </z:row>
        </z:rows>
        <z:foot>
            <z:footer>footer 1</z:footer>
            <z:footer>footer 2</z:footer>
        </z:foot>
    </z:grid>

%{--    Feature: children binding + collection iteration + command binding + property binding + components wiring --}%
    <z:space/>
    <z:grid id="grid2" width="400px" model="@bind(vm.itemList) @template(vm.shouldDisplayInfo ? 'model1' : 'model2')">
        <z:columns>
            <z:column id="column1_grid2" label="Name"/>
            <z:column label="Description"/>
            <z:column label="Actions"/>
        </z:columns>
        <z:template name="model1">
            <row>
                <label value="@bind(each.name)"/>
                <label value="@bind(each.description)"/>
                <button label="popup" onClick="@command('popup')"/>
            </row>
        </z:template>

        <z:template name="model2">
            <row>
                <label value="label 1"/>
                <label value="label 2"/>
                <button label="popup" onClick="@command('popup')"/>
            </row>
        </z:template>
    </z:grid>

%{--    Feature: popup window + property binding + command binding--}%
    <z:window id="popup" mode="modal" visible="@load(vm.shouldDisplayPopup)" border="normal" position="center">
        <z:label value="Here is the content of popup"/>
        <z:space/>
        <z:button label="close" onClick="@command('popup')"/>
    </z:window>

%{--    Grails iteration--}%
    <g:each in="${[1,2,3]}" var="num">
        <p>Number ${num}</p>
    </g:each>

%{--    Grails iteration--}%
    <g:set var="num" value="${1}" />
    <g:while test="${num < 5 }">
        <p>Number ${num++}</p>
    </g:while>

%{--    Feature: retrieve binding parameters--}%
    <z:grid width="700px" model="@bind(vm.itemList)">
        <z:columns>
            <z:column label="index"/>
            <z:column label="name"/>
            <z:column label="action" width="300px"/>
        </z:columns>
        <z:template name="model" var="item">
            <row>
                <label value="@bind(itemStatus.index)"/>
                <label value="@bind(item.name)"/>
                <hbox>
                    <button label="Index" onClick="@command('showIndex', index=itemStatus.index)"/>
                    <button label="Delete" onClick="@command('delete', item=item)"/>
                </hbox>
            </row>
        </z:template>
    </z:grid>

    <z:label value="@bind(vm.message)"/>


</z:window>






</body>
</html>