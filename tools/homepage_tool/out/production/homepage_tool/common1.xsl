<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0"
   xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
   <xsl:output method="xml" indent="yes"/>

   <xsl:template match="root">      
	<root>
	  <xsl:apply-templates select="table"/>
	</root>
   </xsl:template>


   <xsl:template match="table">
    <table>
	    <id><xsl:value-of select="@id"/></id>
	    <fatherid><xsl:value-of select="@fatherid"/></fatherid>
	    <name><xsl:value-of select="@name"/></name>
	    <catalogid><xsl:value-of select="@catalogid"/></catalogid>
	    <billtablename><xsl:value-of select="@billtablename"/></billtablename>
	    <description><xsl:value-of select="@description"/></description>
    	<alias><xsl:value-of select="@alias"/></alias>
        <xsl:apply-templates select="fields"/>
    </table>
   </xsl:template>

   <xsl:template match="fields">
    <fields>
      <xsl:apply-templates select="field"/>	
    </fields>
   </xsl:template>

    <xsl:template match="field">
    <field>
	<name><xsl:value-of select="@name"/></name>
	<label><xsl:value-of select="@label"/></label>
	<type><xsl:value-of select="@type"/></type>
	<length><xsl:value-of select="@length"/></length>
    <classid><xsl:value-of select="@classid"/></classid>
    </field>
   </xsl:template>

</xsl:stylesheet>