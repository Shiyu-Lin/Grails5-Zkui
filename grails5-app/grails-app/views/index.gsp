<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
 "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
%{--    <meta name="layout" content="main"/>--}%
    <title>Welcome to Grails</title>
    <z:resources/>

</head>
<body>
%{--Functional tags--}%

<z:window title="Grid Demo" border="normal">

    <z:vbox>
        <z:paging totalSize="100" pageSize="20"/>
    </z:vbox>

    <z:grid width="600px">
        <z:frozen columns="2"/>
        <z:columns>
            <z:column width="50px">ID</z:column>
            <z:column width="50px">Priority</z:column>
            <z:column width="50px">Status</z:column>
            <z:column width="150px">Summary</z:column>
            <z:column width="250px">Detail</z:column>
            <z:column width="100px">Group</z:column>
            <z:column width="50px">Assign</z:column>
        </z:columns>
        <z:rows>
            <z:row>
                <z:cell>0001</z:cell>
                <z:cell>1</z:cell>
                <z:cell>closed</z:cell>
                <z:cell>Fix login issue</z:cell>
                <z:cell>Login does not work at all</z:cell>
                <z:cell>Account</z:cell>
                <z:cell>Bob</z:cell>
            </z:row>
            <z:row>
                <z:cell>0002</z:cell>
                <z:cell>3</z:cell>
                <z:cell>open</z:cell>
                <z:cell>Button style broken</z:cell>
                <z:cell>Check main.css</z:cell>
                <z:cell>Styling</z:cell>
                <z:cell>Alice</z:cell>
            </z:row>
            <z:row>
                <z:cell>0003</z:cell>
                <z:cell>2</z:cell>
                <z:cell>open</z:cell>
                <z:cell>Client search result</z:cell>
                <z:cell>Search service returns incomplete result</z:cell>
                <z:cell>Service</z:cell>
                <z:cell>Bob</z:cell>
            </z:row>
        </z:rows>
    </z:grid>

    <z:grid>
        <z:columns>
            <z:column label="A"/>
            <z:column label="B"/>
            <z:column label="C"/>
            <z:column label="D"/>
        </z:columns>
        <z:rows>
            <z:row>
                <z:cell rowspan="4" align="center" valign="bottom">
                    <z:label value="item 1"/>
                </z:cell>
                <z:cell colspan="3">
                    <z:label value="item 2"/>
                </z:cell>
            </z:row>
            <z:row>
                <z:cell colspan="2" align="center">
                    <z:label value="item 3"/>
                </z:cell>
                <z:label value="item 4"/>
            </z:row>
            <z:row>
                <z:label value="item 5"/>
                <z:label value="item 6"/>
                <z:label value="item 7"/>
            </z:row>
            <z:row>
                <z:label value="item 8"/>
                <z:label value="item 9"/>
                <z:label value="item 10"/>
            </z:row>
        </z:rows>
    </z:grid>
    <z:space/>
    <z:window title="hbox" border="normal" width="320px">
        <z:hbox width="300px" pack="center">
            <z:cell hflex="1" align="center">
                <z:label value="item 1"/>
            </z:cell>
            <z:cell hflex="1" align="center">
                <z:label value="item 2"/>
            </z:cell>
        </z:hbox>
    </z:window>

    <z:grid>
        <z:auxhead>
            <z:auxheader label="H1'07" colspan="6"/>
            <z:auxheader label="H2'07" colspan="6"/>
        </z:auxhead>
        <z:auxhead>
            <z:auxheader label="Q1" colspan="3"/>
            <z:auxheader label="Q2" colspan="3"/>
            <z:auxheader label="Q3" colspan="3"/>
            <z:auxheader label="Q4" colspan="3"/>
        </z:auxhead>
        <z:columns>
            <z:column label="Jan"/>
            <z:column label="Feb"/>
            <z:column label="Mar"/>
            <z:column label="Apr"/>
            <z:column label="May"/>
            <z:column label="Jun"/>
            <z:column label="Jul"/>
            <z:column label="Aug"/>
            <z:column label="Sep"/>
            <z:column label="Oct"/>
            <z:column label="Nov"/>
            <z:column label="Dec"/>
        </z:columns>
        <z:rows>
            <z:row>
                <z:label value="1,000"/>
                <z:label value="1,100"/>
                <z:label value="1,200"/>
                <z:label value="1,300"/>
                <z:label value="1,400"/>
                <z:label value="1,500"/>
                <z:label value="1,600"/>
                <z:label value="1,700"/>
                <z:label value="1,800"/>
                <z:label value="1,900"/>
                <z:label value="2,000"/>
                <z:label value="2,100"/>
            </z:row>
        </z:rows>
    </z:grid>



    <z:grid width="300px">
        <z:columns>
            <z:column label="Type" width="50px"/>
            <z:column label="Content"/>
        </z:columns>
        <z:rows>
            <z:row>
                <z:label value="File:"/>
                <z:textbox width="99%"/>
            </z:row>
            <z:row>
                <z:label value="Type:"/>
                <z:hbox>
                    <z:listbox rows="1" mold="select">
                        <z:listitem label="Java Files,(*.java)"/>
                        <z:listitem label="All Files,(*.*)"/>
                    </z:listbox>
                    <z:button label="Browse..."/>
                </z:hbox>
            </z:row>
        </z:rows>
        <z:foot>
            <z:footer>footer1</z:footer>
            <z:footer>footer2</z:footer>
        </z:foot>
    </z:grid>

    <z:window>
        <z:label id="now"/>
        <z:timer id="timer" delay="1000" repeats="true" onTimer="now.setValue(new Date().toString())"/>
    </z:window>


    <z:window id="win1">
        <z:button label="change color" onClick="Clients.evalJavaScript('myfunc()');"/>
    </z:window>
    <z:script type="text/javascript">
        function myfunc() {
            jq("$win1").css("backgroundColor", "red");
        }
    </z:script>

    <z:box>
        <z:fileupload label="Upload"/>
    </z:box>

    <z:imagemap src="/assets/apple-touch-icon.png" onClick="alert(event.area)">
        <z:area id="First" coords="0, 0, 100, 100"/>
        <z:area id="Second" shape="circle" coords="200, 200, 100"/>
    </z:imagemap>


    <z:a href="http://www.zkoss.org" label="Visit ZK!"/>
    <z:div align="left" width="300px">
        <z:doublebox/>
    </z:div>
    <z:div align="right" width="300px">
        <z:doublebox/>
    </z:div>


    <z:caption label="This is a caption"/>
    <z:groupbox width="300px">
        <z:caption label="fruits"/>
        <z:radiogroup>
            <z:radio label="Apple"/>
            <z:radio label="Orange"/>
            <z:radio label="Banana"/>
        </z:radiogroup>
    </z:groupbox>

    <z:grid>
        <z:columns sizable="true">
            <z:column label="Type" width="50px"/>
            <z:column label="Content"/>
        </z:columns>
        <z:rows>
            <z:row>
                <z:label value="File:"/>
                <z:textbox width="99%"/>
            </z:row>
            <z:row>
                <z:label value="Type:"/>
                <z:hbox>
                    <z:listbox rows="1" mold="select">
                        <z:listitem label="Java Files,(*.java)"/>
                        <z:listitem label="All Files,(*.*)"/>
                    </z:listbox>
                    <z:button label="Browse..."/>
                </z:hbox>
            </z:row>
            <z:row>
                <z:label value="Options:"/>
                <z:textbox rows="3" width="99%"/>
            </z:row>
        </z:rows>
    </z:grid>

    <z:button label="Hi" onClick="alert('Hi')"/>

    <z:panel height="100px" width="200px" style="margin-bottom:10px" title="Panel1"
             border="normal" maximizable="true" collapsible="true">
        <z:panelchildren>PanelContent1</z:panelchildren>
    </z:panel>
    <z:panel height="100px" width="200px" framable="true" title="Panel2" border="normal"
             maximizable="true" style="margin-bottom:10px">
        <z:panelchildren>PanelContent2</z:panelchildren>
    </z:panel>

    <z:span>
        <z:label value="Name:"/>
        <z:textbox/>
    </z:span>

    <z:tabbox width="400px" height="150px">
        <z:tabs>
            <z:tab label="Tab 1"/>
            <z:tab label="Tab 2" closable="true" />
        </z:tabs>
        <z:tabpanels>
            <z:tabpanel>This is panel 1</z:tabpanel>
            <z:tabpanel>This is panel 2</z:tabpanel>
        </z:tabpanels>
    </z:tabbox>
    <z:space/>
    <z:tabbox width="400px" mold="accordion">
        <z:tabs>
            <z:tab label="Tab 3"/>
            <z:tab label="Tab 4"/>
        </z:tabs>
        <z:tabpanels>
            <z:tabpanel>This is panel 3</z:tabpanel>
            <z:tabpanel>This is panel 4</z:tabpanel>
        </z:tabpanels>
    </z:tabbox>

</z:window>

<z:window title="Embedded Style" border="normal" width="200px">Hello,
        Embedded!</z:window>
<z:window title="Overlapped Style" top="100px" mode="overlapped" border="normal"
          width="200px">Hello, Overlapped!</z:window>
<z:window title="Popup Style" top="200px" mode="popup" border="normal"
          width="200px">Hello, Popup!</z:window>

<z:image src="/assets/apple-touch-icon.png"/>
<g:img dir="images" file="apple-touch-icon.png" width="40" height="40"/>


<z:window id="win" title="Html Demo" border="normal">
    <z:html>
        <span id="...">
            <h4>Hi, Html Demo</h4>
            <p>It is the content of the html component.</p>
        </span>
    </z:html>
</z:window>

<z:window id="win" title="This is an Iframe Demo!">
    <z:iframe style="width:99%; height:400px;border:3px inset;" src="https://docs.grails.org/5.3.2/guide/single.html" />
</z:window>

<z:space/>
<z:space/>

<z:image src="/assets/earth.jpg" onClick="alert(event.x + ',' +event.y)" height="400px" width="350px"/>

<z:space/>

<z:window title="include demo" border="normal">
    Hello, World!
    <z:include src="/helloWorld.gsp"/>
</z:window>

<z:space/>

<z:window title="Label Demo" width="400px">
    <z:grid>
        <z:rows>
            <z:row><z:label value="Label(normal):"/><z:label id="lb1" value="Hello World"/></z:row>
            <z:row><z:label value="Label(color): "/><z:label id="lb2" value="Hello World" style="color:red"/></z:row>
            <z:row><z:label value="Label(font): "/><z:label id="lb3" value="Hello World" style="font-weight:bold"/></z:row>
            <z:row><z:label value="Label(size): "/><z:label id="lb4" value="Hello World" style="font-size:14pt"/></z:row>
            <z:row><z:label value="Label(maxlength): "/><z:label value="Hello World" id="lb5" maxlength="5"/></z:row>
            <z:row><z:label value="Label(pre): "/><z:label value="Hello World" id="lb6" pre="true"/></z:row>
        </z:rows>
    </z:grid>
</z:window>

<z:space/>

<z:menubar id="menubar">
    <z:menu label="File">
        <z:menupopup>
            <z:menuitem label="New"/>
            <z:menuitem label="Open"/>
            <z:menuitem label="Save"/>
            <z:menuseparator/>
            <z:menuitem label="Exit"/>
        </z:menupopup>
    </z:menu>
    <z:menu label="Help">
        <z:menupopup>
            <z:menuitem label="Index"/>
            <z:menu label="About">
                <z:menupopup>
                    <z:menuitem label="About ZK"/>
                    <z:menuitem label="About Potix"/>
                </z:menupopup>
            </z:menu>
        </z:menupopup>
    </z:menu>
</z:menubar>

<z:space/>

<z:separator bar="true"/>
<z:label value="Tooptip for Another Popup" tooltip="any"/>
<z:popup id="any" width="500px">
    <z:vbox>
        <z:label value="ZK simply rich."/>
        <z:toolbarbutton label="ZK your killer Web application now!"
                         href="http://www.zkoss.org"/>
    </z:vbox>
</z:popup>

<z:space/>

<z:progressmeter value="35"/>


<z:label value="line 1 by separator"/>
<z:separator/>
<z:label value="line 2 by separator"/>
<z:separator/>
<z:label value="line 3 by separator"/>
<z:space bar="true"/>
<z:label value="another piece"/>
<z:separator spacing="20px"/>
<z:label value="line 4 by separator"/>
<z:space bar="true" spacing="20px"/>
<z:label value="another piece"/>

<z:space bar="true"/>
<z:space bar="true"/>
<z:space bar="true"/>
<z:space bar="true"/>


<z:window title="Toolbar window" border="normal">
    <z:toolbar>
        <z:toolbarbutton label="Left"/>
        <z:space/>
        <z:toolbarbutton label="Right" image="/assets/apple-touch-icon.png"
                         dir="reverse"/>
    </z:toolbar>
    <z:toolbar orient="vertical">
        <z:button label="Left" image="/assets/apple-touch-icon.png" width="125px"/>
        <z:toolbarbutton label="Right" image="/assets/apple-touch-icon.png"
                         dir="reverse"/>
    </z:toolbar>
</z:window>


<z:window title="toolbar demo" border="normal">
    <z:caption>
        <z:toolbarbutton label="button3" image="/assets/apple-touch-icon.png"/>
        <z:space/>
        <z:toolbarbutton label="button4" image="/assets/apple-touch-icon.png" dir="reverse"/>
    </z:caption>
    <z:toolbar>
        <z:toolbarbutton label="button1" image="/assets/apple-touch-icon.png"/>
        <z:space/>
        <z:toolbarbutton label="button2" image="/assets/apple-touch-icon.png"/>
    </z:toolbar>
    <z:hbox>
        <z:toolbarbutton label="button5" image="/assets/apple-touch-icon.png" orient="vertical"/>
        <z:space/>
        <z:toolbarbutton label="button6" image="/assets/apple-touch-icon.png" orient="vertical" dir="reverse"/>
    </z:hbox>
</z:window>



<z:bandbox>
    <z:bandpopup>
        <z:vbox>
            <z:hbox>
                <z:label value="Search"/>
                <z:textbox/>
            </z:hbox>
            <z:listbox width="200px">
                <z:listhead>
                    <z:listheader label="Name"/>
                    <z:listheader label="Description"/>
                </z:listhead>
                <z:listitem>
                    <z:listcell label="John"/>
                    <z:listcell label="CEO"/>
                </z:listitem>
                <z:listitem>
                    <z:listcell label="Joe"/>
                    <z:listcell label="Engineer"/>
                </z:listitem>
                <z:listitem>
                    <z:listcell label="Mary"/>
                    <z:listcell label="Supervisor"/>
                </z:listitem>
            </z:listbox>
        </z:vbox>
    </z:bandpopup>
</z:bandbox>


<z:hlayout>
    <z:calendar id="cal"/>
    <z:datebox id="in"/>
</z:hlayout>


<z:window title="Checkbox demo" border="normal" width="350px">
    <z:checkbox id="apple" label="Apple"/>
    <z:checkbox id="orange" label="Orange"/>
    <z:checkbox id="banana" label="Banana"/>
</z:window>

<z:combobox>
    <z:comboitem label="Simple and Rich"/>
    <z:comboitem label="Cool!"/>
    <z:comboitem label="Ajax and RIA"/>
</z:combobox>

<z:datebox lenient="true" buttonVisible="false"/>
<z:datebox lenient="false" compact="false" buttonVisible="true"/>

<z:decimalbox format="#,##0.##"/>

<z:doublebox value="2.3"/>

<z:window title="Double Spinner" border="normal" width="300px">
    <z:doublespinner step="0.5"/>
</z:window>

<z:window title="Intbox Demo" border="normal" width="300px">
    <z:label value="int box:"/>
    <z:intbox/>
</z:window>


<z:window title="Radiobox &amp; Radio Demo" width="400px" border="normal">
    <z:vbox>
        <z:radiogroup>
            <z:radio label="Apple"/>
            <z:radio label="Orange"/>
            <z:radio label="Banana"/>
        </z:radiogroup>
    </z:vbox>
</z:window>

<z:slider id="slider" orient="vertical"/>
<z:slider curpos="1" maxpos="20"/>

<z:window title="Spinner" border="normal" width="150px">
    <z:spinner/>
</z:window>

<z:textbox value="text..."/>
<z:textbox value="secret" type="password"/>
<z:textbox constraint="/.+@.+\\.[a-z]+/: Please enter an e-mail address"/>

<z:window title="Simple" width="300px" border="normal">
    <z:timebox id="tb0"/>
</z:window>



<z:borderlayout height="450px">
    <z:north title="North" maxsize="300" size="50%" splittable="true" collapsible="true">
        <z:borderlayout>
            <z:west title="West" size="25%" flex="true" maxsize="250" splittable="true" collapsible="true">
                <z:div style="background:#B8D335">
                    <z:label value="25%"
                             style="color:white;font-size:50px"/>
                </z:div>
            </z:west>
            <z:center border="none" flex="true">
                <z:div style="background:#E6D92C">
                    <z:label value="25%"
                             style="color:white;font-size:50px"/>
                </z:div>
            </z:center>
            <z:east size="50%" border="none" flex="true">
                <z:label value="Here is a non-border"
                         style="color:gray;font-size:30px"/>
            </z:east>
        </z:borderlayout>
    </z:north>
    <z:center border="0">
        <z:borderlayout>
            <z:west maxsize="600" size="30%" flex="true" border="0" splittable="true">
                <z:div style="background:#E6D92C">
                    <z:label value="30%"
                             style="color:white;font-size:50px"/>
                </z:div>
            </z:west>
            <z:center>
                <z:label value="Here is a border"
                         style="color:gray;font-size:30px"/>
            </z:center>
            <z:east title="East" size="30%" flex="true" collapsible="true">
                <z:div style="background:#B8D335">
                    <z:label value="30%"
                             style="color:white;font-size:50px"/>
                </z:div>
            </z:east>
        </z:borderlayout>
    </z:center>
</z:borderlayout>

<z:box orient="vertical">
    <z:button label="Button 1"/>
    <z:button label="Button 2"/>
</z:box>
<z:box orient="horizontal">
    <z:button label="Button 3"/>
    <z:button label="Button 4"/>
</z:box>

<z:vbox>
    <z:button label="Button 1"/>
    <z:button label="Button 2"/>
</z:vbox>
<z:hbox>
    <z:button label="Button 3"/>
    <z:button label="Button 4"/>
</z:hbox>

<z:vlayout>
    <z:button label="Button 1"/>
    <z:button label="Button 2"/>
</z:vlayout>
<z:hlayout>
    <z:button label="Button 3"/>
    <z:button label="Button 4"/>
</z:hlayout>


<z:hbox spacing="0" width="100%">
    <z:vbox height="200px">
        <z:label value="Column 1-1: The left-top box. To know whether a splitter is collapsed,you can listen to the onOpen event."/>
        <z:splitter collapse="after" />
        <z:label value="Column 1-2: You can enforce to open or collapse programming by calling setOpen method."/>
    </z:vbox>
    <z:splitter collapse="before" />
    <z:label value="Column 2: Whether a splitter allows users to open or collapse depending on the collapse attribue."/>
</z:hbox>

<z:window title="listbox demo" border="normal" width="400px">
    <z:listbox id="box">
        <z:listhead sizable="true">
            <z:listheader label="name" sort="auto"/>
            <z:listheader label="gender" sort="auto"/>
        </z:listhead>
        <z:listitem>
            <z:listcell label="Mary"/>
            <z:listcell label="FEMALE"/>
        </z:listitem>
        <z:listitem>
            <z:listcell label="John"/>
            <z:listcell label="MALE"/>
        </z:listitem>
        <z:listitem>
            <z:listcell label="Jane"/>
            <z:listcell label="FEMALE"/>
        </z:listitem>
        <z:listitem>
            <z:listcell label="Henry"/>
            <z:listcell label="MALE"/>
        </z:listitem>
        <z:listfoot>
            <z:listfooter>
                <z:label value="This is footer1"/>
            </z:listfooter>
            <z:listfooter>
                <z:label value="This is footer2"/>
            </z:listfooter>
        </z:listfoot>
    </z:listbox>
</z:window>



<z:window title="tree demo" border="normal" width="400px">
    <z:tree id="tree" rows="5">
        <z:treecols sizable="true">
            <z:treecol label="Name"/>
            <z:treecol label="Description"/>
        </z:treecols>
        <z:treechildren>
            <z:treeitem>
                <z:treerow>
                    <z:treecell label="Item 1"/>
                    <z:treecell label="Item 1 description"/>
                </z:treerow>
            </z:treeitem>
            <z:treeitem>
                <z:treerow>
                    <z:treecell label="Item 2"/>
                    <z:treecell label="Item 2 description"/>
                </z:treerow>
                <z:treechildren>
                    <z:treeitem>
                        <z:treerow>
                            <z:treecell label="Item 2.1"/>
                        </z:treerow>
                    </z:treeitem>
                    <z:treeitem>
                        <z:treerow>
                            <z:treecell label="Item 2.2"/>
                            <z:treecell label="Item 2.2 is something who cares"/>
                        </z:treerow>
                    </z:treeitem>
                </z:treechildren>
            </z:treeitem>
            <z:treeitem label="Item 3"/>
        </z:treechildren>
        <z:treefoot>
            <z:treefooter label="Count"/>
            <z:treefooter label="Summary"/>
        </z:treefoot>
    </z:tree>
</z:window>







%{--Tags with issues--}%










</body>
</html>
