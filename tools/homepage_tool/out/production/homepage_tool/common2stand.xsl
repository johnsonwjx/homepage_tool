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
	<xsl:element name="table">
	      <xsl:attribute name="id"><xsl:value-of select="id"/></xsl:attribute>
	      <xsl:attribute name="fatherid"><xsl:value-of select="fatherid"/></xsl:attribute>
	      <xsl:attribute name="name"><xsl:value-of select="name"/></xsl:attribute>
	      <xsl:attribute name="description"><xsl:value-of select="description"/></xsl:attribute>
	      <xsl:attribute name="alias"><xsl:value-of select="alias"/></xsl:attribute>
	      <xsl:attribute name="codecontinuous"></xsl:attribute>
	      <xsl:attribute name="catalogid"><xsl:value-of select="catalogid"/></xsl:attribute>
	      <xsl:attribute name="billtablename"></xsl:attribute>
	      <xsl:attribute name="codefmt"></xsl:attribute>
	      <xsl:attribute name="componentid"></xsl:attribute>
	      <xsl:attribute name="cname"></xsl:attribute>
	      <xsl:attribute name="fathertablename"></xsl:attribute>
	      <xsl:attribute name="mode"></xsl:attribute>
	      <xsl:attribute name="keyfieldname"></xsl:attribute>
	      <xsl:attribute name="code"></xsl:attribute>
	      <xsl:attribute name="billtablename"><xsl:value-of select="billtablename"/></xsl:attribute>
	  <xsl:apply-templates select="indexs"/>
          <xsl:apply-templates select="fields"/>
	</xsl:element>
   </xsl:template>

   <xsl:template match="indexs">
    <indexs>
    </indexs>
   </xsl:template>


   <xsl:template match="fields">
    <fields>
     <xsl:apply-templates select="field"/>	
    </fields>
   </xsl:template>

   <xsl:template match="field">
	<xsl:element name="field">
	      <xsl:attribute name="name"><xsl:value-of select="name"/></xsl:attribute>
	      <xsl:attribute name="label"><xsl:value-of select="label"/></xsl:attribute>
	      <xsl:attribute name="type"><xsl:value-of select="type"/></xsl:attribute>
	      <xsl:attribute name="length"><xsl:value-of select="length"/></xsl:attribute>
	      <xsl:attribute name="dec"></xsl:attribute>
	      <xsl:attribute name="description"></xsl:attribute>
	      <xsl:attribute name="default"></xsl:attribute>
	      <xsl:attribute name="required"></xsl:attribute>
	      <xsl:attribute name="isshow"></xsl:attribute>
	      <xsl:attribute name="isdept"></xsl:attribute>
	      <xsl:attribute name="ischeckrepeat"></xsl:attribute>
	      <xsl:attribute name="foreignkey"></xsl:attribute>
	      <xsl:attribute name="classid"><xsl:value-of select="classid"/></xsl:attribute>
	      <xsl:attribute name="worktype"></xsl:attribute>
	</xsl:element>
   </xsl:template>

</xsl:stylesheet>