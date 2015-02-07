<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0"
   xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
   <xsl:output method="xml" indent="yes"/>

   <xsl:template match="InitDataList/InitData[@name='DDATA']">      
	<root>
	  <xsl:apply-templates select="Table"/>
	</root>
   </xsl:template>



   <xsl:template match="Table">
	<xsl:element name="table">
	      <xsl:attribute name="id"></xsl:attribute>
	      <xsl:attribute name="fatherid"></xsl:attribute>
	      <xsl:attribute name="name"><xsl:value-of select="TABLE_NAME"/></xsl:attribute>
	      <xsl:attribute name="description"><xsl:value-of select="TABLE_DESC"/></xsl:attribute>
	      <xsl:attribute name="alias"></xsl:attribute>
	      <xsl:attribute name="codecontinuous"></xsl:attribute>
	      <xsl:attribute name="catalogid"></xsl:attribute>
	      <xsl:attribute name="billtablename"></xsl:attribute>
	      <xsl:attribute name="codefmt"></xsl:attribute>
	      <xsl:attribute name="componentid"></xsl:attribute>
	      <xsl:attribute name="cname"></xsl:attribute>
	      <xsl:attribute name="fathertablename"></xsl:attribute>
	      <xsl:attribute name="mode"></xsl:attribute>
	      <xsl:attribute name="keyfieldname"></xsl:attribute>
	      <xsl:attribute name="code"></xsl:attribute>
              <xsl:attribute name="billtablename"></xsl:attribute>

	  <xsl:apply-templates select="indexs"/>
          <fields><xsl:apply-templates select="Field"/></fields>
	</xsl:element>
   </xsl:template>

   <xsl:template match="indexs">
    <indexs>
       <xsl:apply-templates select="index"/>	
    </indexs>
   </xsl:template>

      <xsl:template match="index">
	<xsl:element name="index">
	      <xsl:attribute name="name"><xsl:value-of select="name"/></xsl:attribute>
	       <xsl:attribute name="fields"><xsl:value-of select="fields"/></xsl:attribute>
	        <xsl:attribute name="type"><xsl:value-of select="type"/></xsl:attribute>
	</xsl:element>
   </xsl:template>


   <xsl:template match="Field">
	<xsl:element name="field">
	      <xsl:attribute name="name"><xsl:value-of select="Para[@name='FIELD_NAME']"/></xsl:attribute>
	      <xsl:attribute name="label"><xsl:value-of select="Para[@name='FIELD_DESC']"/></xsl:attribute>
	      <xsl:attribute name="type"><xsl:value-of select="Para[@name='FIELD_TYPE']"/></xsl:attribute>
	      <xsl:attribute name="length"><xsl:value-of select="Para[@name='FIELD_LENGTH']"/></xsl:attribute>
	      <xsl:attribute name="dec"><xsl:value-of select="Para[@name='FIELD_DEC']"/></xsl:attribute>
	      <xsl:attribute name="description"></xsl:attribute>
	      <xsl:attribute name="default"><xsl:value-of select="Para[@name='FIELD_DEFAULT']"/></xsl:attribute>
	      <xsl:attribute name="null"><xsl:value-of select="Para[@name='FIELD_NULL']"/></xsl:attribute>
	      <xsl:attribute name="required"></xsl:attribute>
	      <xsl:attribute name="isshow"></xsl:attribute>
	      <xsl:attribute name="isdept"></xsl:attribute>
	      <xsl:attribute name="ischeckrepeat"></xsl:attribute>
	      <xsl:attribute name="foreignkey"></xsl:attribute>
	      <xsl:attribute name="classid"></xsl:attribute>
	      <xsl:attribute name="worktype"></xsl:attribute>
	</xsl:element>
   </xsl:template>

</xsl:stylesheet>