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

<z:window apply="org.zkoss.bind.BindComposer" viewModel="@id('vm') @init('zkMvvmViewModel')">

    <z:intbox value="@bind(vm.index) @validator(vm.rangeValidator)"/>

    <z:doublebox value="@bind(vm.price)"/>

    <z:toolbar>
        <z:button label="+index" onClick="@command('incrementIndexByTen')" />
        <z:button label="-price" onClick="@command('decrementPriceByFive')" />
    </z:toolbar>

    <z:div>
        <z:label value="ViewModel yea!!!"/>
    </z:div>

%{--    <!-- other components -->--}%

    <z:grid width="300px" mold="paging" pageSize="4">
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
    </z:grid>


</z:window>




</body>
</html>