# Add project specific ProGuard rules here.
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascriptToInject.interface.for.webview {
#   public *;
#}

-dontwarn androidx.compose.animation.tooling.ComposeAnimation
-dontwarn androidx.compose.animation.tooling.ComposeAnimationType
-dontwarn androidx.compose.ui.layout.LookaheadLayoutKt
-dontwarn androidx.window.extensions.WindowExtensions
-dontwarn androidx.window.extensions.WindowExtensionsProvider
-dontwarn androidx.window.extensions.layout.WindowLayoutComponent
-dontwarn androidx.window.sidecar.SidecarDisplayFeature
-dontwarn coil.request.ImageResult$Metadata
-dontwarn com.google.errorprone.annotations.CanIgnoreReturnValue
-dontwarn com.google.errorprone.annotations.CheckReturnValue
-dontwarn com.google.errorprone.annotations.Immutable
-dontwarn com.google.errorprone.annotations.RestrictedApi
-dontwarn com.google.firebase.crashlytics.buildtools.reloc.afu.org.checkerframework.checker.formatter.qual.ConversionCategory
-dontwarn com.google.firebase.crashlytics.buildtools.reloc.org.checkerframework.checker.formatter.qual.ConversionCategory
-dontwarn com.microsoft.schemas.office.office.CTCallout
-dontwarn com.microsoft.schemas.office.office.CTClipPath
-dontwarn com.microsoft.schemas.office.office.CTDiagram
-dontwarn com.microsoft.schemas.office.office.CTEquationXml
-dontwarn com.microsoft.schemas.office.office.CTExtrusion
-dontwarn com.microsoft.schemas.office.office.CTFill
-dontwarn com.microsoft.schemas.office.office.CTInk
-dontwarn com.microsoft.schemas.office.office.CTRegroupTable
-dontwarn com.microsoft.schemas.office.office.CTRules
-dontwarn com.microsoft.schemas.office.office.CTSkew
-dontwarn com.microsoft.schemas.office.office.CTStrokeChild
-dontwarn com.microsoft.schemas.office.office.STDiagramLayout
-dontwarn com.microsoft.schemas.office.office.STOLELinkType
-dontwarn com.microsoft.schemas.office.office.STOLEUpdateMode
-dontwarn com.microsoft.schemas.office.office.STScreenSize
-dontwarn com.microsoft.schemas.office.powerpoint.CTEmpty
-dontwarn com.microsoft.schemas.office.powerpoint.CTRel
-dontwarn com.microsoft.schemas.office.visio.x2012.main.AttachedToolbarsType
-dontwarn com.microsoft.schemas.office.visio.x2012.main.ColorsType
-dontwarn com.microsoft.schemas.office.visio.x2012.main.CpType
-dontwarn com.microsoft.schemas.office.visio.x2012.main.CustomMenusFileType
-dontwarn com.microsoft.schemas.office.visio.x2012.main.CustomToolbarsFileType
-dontwarn com.microsoft.schemas.office.visio.x2012.main.DataType
-dontwarn com.microsoft.schemas.office.visio.x2012.main.DocumentSheetType
-dontwarn com.microsoft.schemas.office.visio.x2012.main.DynamicGridEnabledType
-dontwarn com.microsoft.schemas.office.visio.x2012.main.EventListType
-dontwarn com.microsoft.schemas.office.visio.x2012.main.FaceNamesType
-dontwarn com.microsoft.schemas.office.visio.x2012.main.FldType
-dontwarn com.microsoft.schemas.office.visio.x2012.main.ForeignDataType
-dontwarn com.microsoft.schemas.office.visio.x2012.main.GlueSettingsType
-dontwarn com.microsoft.schemas.office.visio.x2012.main.HeaderFooterType
-dontwarn com.microsoft.schemas.office.visio.x2012.main.IconType
-dontwarn com.microsoft.schemas.office.visio.x2012.main.MasterShortcutType
-dontwarn com.microsoft.schemas.office.visio.x2012.main.PpType
-dontwarn com.microsoft.schemas.office.visio.x2012.main.ProtectBkgndsType
-dontwarn com.microsoft.schemas.office.visio.x2012.main.ProtectMastersType
-dontwarn com.microsoft.schemas.office.visio.x2012.main.ProtectShapesType
-dontwarn com.microsoft.schemas.office.visio.x2012.main.ProtectStylesType
-dontwarn com.microsoft.schemas.office.visio.x2012.main.PublishSettingsType
-dontwarn com.microsoft.schemas.office.visio.x2012.main.RefByType
-dontwarn com.microsoft.schemas.office.visio.x2012.main.SnapAnglesType
-dontwarn com.microsoft.schemas.office.visio.x2012.main.SnapExtensionsType
-dontwarn com.microsoft.schemas.office.visio.x2012.main.SnapSettingsType
-dontwarn com.microsoft.schemas.office.visio.x2012.main.TpType
-dontwarn com.microsoft.schemas.office.word.CTBorder
-dontwarn com.microsoft.schemas.office.word.STHorizontalAnchor
-dontwarn com.microsoft.schemas.office.word.STVerticalAnchor
-dontwarn com.microsoft.schemas.office.word.STWrapSide
-dontwarn com.microsoft.schemas.office.x2006.digsig.STPositiveInteger
-dontwarn com.microsoft.schemas.office.x2006.digsig.STSignatureProviderUrl
-dontwarn com.microsoft.schemas.office.x2006.digsig.STSignatureText
-dontwarn com.microsoft.schemas.office.x2006.digsig.STVersion
-dontwarn com.microsoft.schemas.vml.CTArc
-dontwarn com.microsoft.schemas.vml.CTCurve
-dontwarn com.microsoft.schemas.vml.CTImage
-dontwarn com.microsoft.schemas.vml.CTPolyLine
-dontwarn com.microsoft.schemas.vml.STImageAspect
-dontwarn com.microsoft.schemas.vml.STStrokeArrowLength
-dontwarn com.microsoft.schemas.vml.STStrokeArrowWidth
-dontwarn com.microsoft.schemas.vml.STStrokeEndCap
-dontwarn com.microsoft.schemas.vml.STStrokeLineStyle
-dontwarn java.lang.management.ManagementFactory
-dontwarn java.lang.management.RuntimeMXBean
-dontwarn javax.xml.crypto.dsig.TransformService
-dontwarn javax.xml.stream.Location
-dontwarn javax.xml.stream.XMLStreamException
-dontwarn javax.xml.stream.XMLStreamReader
-dontwarn net.sf.saxon.Configuration
-dontwarn net.sf.saxon.dom.DOMNodeWrapper
-dontwarn net.sf.saxon.dom.DocumentWrapper
-dontwarn net.sf.saxon.dom.NodeOverNodeInfo
-dontwarn net.sf.saxon.lib.ConversionRules
-dontwarn net.sf.saxon.ma.map.HashTrieMap
-dontwarn net.sf.saxon.om.GroundedValue
-dontwarn net.sf.saxon.om.Item
-dontwarn net.sf.saxon.om.NodeInfo
-dontwarn net.sf.saxon.om.Sequence
-dontwarn net.sf.saxon.om.SequenceTool
-dontwarn net.sf.saxon.om.StructuredQName
-dontwarn net.sf.saxon.query.DynamicQueryContext
-dontwarn net.sf.saxon.query.StaticQueryContext
-dontwarn net.sf.saxon.query.XQueryExpression
-dontwarn net.sf.saxon.str.StringView
-dontwarn net.sf.saxon.str.UnicodeString
-dontwarn net.sf.saxon.sxpath.IndependentContext
-dontwarn net.sf.saxon.sxpath.XPathDynamicContext
-dontwarn net.sf.saxon.sxpath.XPathEvaluator
-dontwarn net.sf.saxon.sxpath.XPathExpression
-dontwarn net.sf.saxon.sxpath.XPathStaticContext
-dontwarn net.sf.saxon.sxpath.XPathVariable
-dontwarn net.sf.saxon.trans.XPathException
-dontwarn net.sf.saxon.tree.wrapper.VirtualNode
-dontwarn net.sf.saxon.type.BuiltInAtomicType
-dontwarn net.sf.saxon.type.ConversionResult
-dontwarn net.sf.saxon.value.AnyURIValue
-dontwarn net.sf.saxon.value.AtomicValue
-dontwarn net.sf.saxon.value.BigDecimalValue
-dontwarn net.sf.saxon.value.BigIntegerValue
-dontwarn net.sf.saxon.value.BooleanValue
-dontwarn net.sf.saxon.value.CalendarValue
-dontwarn net.sf.saxon.value.DateTimeValue
-dontwarn net.sf.saxon.value.DateValue
-dontwarn net.sf.saxon.value.DoubleValue
-dontwarn net.sf.saxon.value.DurationValue
-dontwarn net.sf.saxon.value.FloatValue
-dontwarn net.sf.saxon.value.GDateValue
-dontwarn net.sf.saxon.value.GDayValue
-dontwarn net.sf.saxon.value.GMonthDayValue
-dontwarn net.sf.saxon.value.GMonthValue
-dontwarn net.sf.saxon.value.GYearMonthValue
-dontwarn net.sf.saxon.value.GYearValue
-dontwarn net.sf.saxon.value.HexBinaryValue
-dontwarn net.sf.saxon.value.Int64Value
-dontwarn net.sf.saxon.value.ObjectValue
-dontwarn net.sf.saxon.value.QNameValue
-dontwarn net.sf.saxon.value.SaxonDuration
-dontwarn net.sf.saxon.value.SaxonXMLGregorianCalendar
-dontwarn net.sf.saxon.value.StringValue
-dontwarn net.sf.saxon.value.TimeValue
-dontwarn org.apache.batik.anim.dom.SAXSVGDocumentFactory
-dontwarn org.apache.batik.bridge.BridgeContext
-dontwarn org.apache.batik.bridge.DocumentLoader
-dontwarn org.apache.batik.bridge.GVTBuilder
-dontwarn org.apache.batik.bridge.UserAgent
-dontwarn org.apache.batik.bridge.UserAgentAdapter
-dontwarn org.apache.batik.util.XMLResourceDescriptor
-dontwarn org.apache.log.Hierarchy
-dontwarn org.apache.log.Logger
-dontwarn org.apache.log4j.Level
-dontwarn org.apache.log4j.Logger
-dontwarn org.apache.log4j.Priority
-dontwarn org.etsi.uri.x01903.v13.CertifiedRolesListType
-dontwarn org.etsi.uri.x01903.v13.CounterSignatureType
-dontwarn org.etsi.uri.x01903.v13.DocumentationReferencesType
-dontwarn org.etsi.uri.x01903.v13.IncludeType
-dontwarn org.etsi.uri.x01903.v13.OtherCertStatusRefsType
-dontwarn org.etsi.uri.x01903.v13.OtherCertStatusValuesType
-dontwarn org.etsi.uri.x01903.v13.QualifierType
-dontwarn org.etsi.uri.x01903.v13.ReferenceInfoType
-dontwarn org.etsi.uri.x01903.v13.SignatureProductionPlaceType
-dontwarn org.etsi.uri.x01903.v13.UnsignedDataObjectPropertiesType
-dontwarn org.ietf.jgss.GSSCredential
-dontwarn org.openxmlformats.schemas.drawingml.x2006.chart.CTBandFmts
-dontwarn org.openxmlformats.schemas.drawingml.x2006.chart.CTBubbleScale
-dontwarn org.openxmlformats.schemas.drawingml.x2006.chart.CTDLbl
-dontwarn org.openxmlformats.schemas.drawingml.x2006.chart.CTDTable
-dontwarn org.openxmlformats.schemas.drawingml.x2006.chart.CTDispUnits
-dontwarn org.openxmlformats.schemas.drawingml.x2006.chart.CTExtension
-dontwarn org.openxmlformats.schemas.drawingml.x2006.chart.CTLblOffset
-dontwarn org.openxmlformats.schemas.drawingml.x2006.chart.CTMultiLvlStrRef
-dontwarn org.openxmlformats.schemas.drawingml.x2006.chart.CTPictureOptions
-dontwarn org.openxmlformats.schemas.drawingml.x2006.chart.CTPivotFmts
-dontwarn org.openxmlformats.schemas.drawingml.x2006.chart.CTPivotSource
-dontwarn org.openxmlformats.schemas.drawingml.x2006.chart.CTProtection
-dontwarn org.openxmlformats.schemas.drawingml.x2006.chart.CTSizeRepresents
-dontwarn org.openxmlformats.schemas.drawingml.x2006.chart.CTSkip
-dontwarn org.openxmlformats.schemas.drawingml.x2006.chart.CTStockChart
-dontwarn org.openxmlformats.schemas.drawingml.x2006.chart.CTStyle
-dontwarn org.openxmlformats.schemas.drawingml.x2006.chart.CTTextLanguageID
-dontwarn org.openxmlformats.schemas.drawingml.x2006.chart.CTTimeUnit
-dontwarn org.openxmlformats.schemas.drawingml.x2006.chart.CTTrendline
-dontwarn org.openxmlformats.schemas.drawingml.x2006.chart.CTUpDownBars
-dontwarn org.openxmlformats.schemas.drawingml.x2006.chart.STAxisUnit
-dontwarn org.openxmlformats.schemas.drawingml.x2006.chart.STFirstSliceAng
-dontwarn org.openxmlformats.schemas.drawingml.x2006.chart.STMarkerSize
-dontwarn org.openxmlformats.schemas.drawingml.x2006.chart.STPageSetupOrientation
-dontwarn org.openxmlformats.schemas.drawingml.x2006.chart.STPerspective
-dontwarn org.openxmlformats.schemas.drawingml.x2006.chart.STRotX
-dontwarn org.openxmlformats.schemas.drawingml.x2006.chart.STRotY
-dontwarn org.openxmlformats.schemas.drawingml.x2006.chart.STThickness
-dontwarn org.openxmlformats.schemas.drawingml.x2006.diagram.STModelId
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaBiLevelEffect
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaCeilingEffect
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaFloorEffect
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaInverseEffect
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaModulateEffect
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaOutsetEffect
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaReplaceEffect
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTAngle
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTAnimationElementChoice
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTAudioCD
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTAudioFile
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTBackdrop
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTBevel
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTBiLevelEffect
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTBlendEffect
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTBlurEffect
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTBoolean
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTCamera
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTCell3D
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTColorChangeEffect
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTColorReplaceEffect
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTColorSchemeList
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTComplementTransform
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTConnectorLocking
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTCustomColorList
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTEffectReference
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTEmbeddedWAVAudioFile
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTFillEffect
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTFillOverlayEffect
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTFlatText
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTGammaTransform
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTGlowEffect
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTGrayscaleEffect
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTGrayscaleTransform
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTGroupLocking
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTHSLEffect
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTHeaders
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTInnerShadowEffect
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTInverseGammaTransform
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTInverseTransform
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTLightRig
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTLuminanceEffect
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTObjectStyleDefaults
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveFixedAngle
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTPresetShadowEffect
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTPresetTextShape
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTQuickTimeFile
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTReflectionEffect
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTRelativeOffsetEffect
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTShapeLocking
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTSoftEdgesEffect
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTSupplementalFont
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTTableBackgroundStyle
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellBorderStyle
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTTextUnderlineFillGroupWrapper
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTTextUnderlineLineFollowText
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTTintEffect
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.CTTransformEffect
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.STEffectContainerType
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.STFixedAngle
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.STPresetMaterialType
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.STShapeID
-dontwarn org.openxmlformats.schemas.drawingml.x2006.main.STTextColumnCount
-dontwarn org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTRel
-dontwarn org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapThrough
-dontwarn org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapTight
-dontwarn org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapTopBottom
-dontwarn org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.STAlignH
-dontwarn org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.STAlignV
-dontwarn org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTArray
-dontwarn org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTEmpty
-dontwarn org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTNull
-dontwarn org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVstream
-dontwarn org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.STCy
-dontwarn org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.STError
-dontwarn org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.STVectorBaseType
-dontwarn org.openxmlformats.schemas.officeDocument.x2006.math.CTAcc
-dontwarn org.openxmlformats.schemas.officeDocument.x2006.math.CTBar
-dontwarn org.openxmlformats.schemas.officeDocument.x2006.math.CTBorderBox
-dontwarn org.openxmlformats.schemas.officeDocument.x2006.math.CTBox
-dontwarn org.openxmlformats.schemas.officeDocument.x2006.math.CTChar
-dontwarn org.openxmlformats.schemas.officeDocument.x2006.math.CTEqArr
-dontwarn org.openxmlformats.schemas.officeDocument.x2006.math.CTF
-dontwarn org.openxmlformats.schemas.officeDocument.x2006.math.CTFunc
-dontwarn org.openxmlformats.schemas.officeDocument.x2006.math.CTGroupChr
-dontwarn org.openxmlformats.schemas.officeDocument.x2006.math.CTLimLow
-dontwarn org.openxmlformats.schemas.officeDocument.x2006.math.CTLimUpp
-dontwarn org.openxmlformats.schemas.officeDocument.x2006.math.CTMathPr
-dontwarn org.openxmlformats.schemas.officeDocument.x2006.math.CTNary
-dontwarn org.openxmlformats.schemas.officeDocument.x2006.math.CTOMathArgPr
-dontwarn org.openxmlformats.schemas.officeDocument.x2006.math.CTOMathParaPr
-dontwarn org.openxmlformats.schemas.officeDocument.x2006.math.CTOnOff
-dontwarn org.openxmlformats.schemas.officeDocument.x2006.math.CTPhant
-dontwarn org.openxmlformats.schemas.officeDocument.x2006.math.CTRPR
-dontwarn org.openxmlformats.schemas.officeDocument.x2006.math.CTRad
-dontwarn org.openxmlformats.schemas.officeDocument.x2006.math.CTSPre
-dontwarn org.openxmlformats.schemas.officeDocument.x2006.math.CTSSubSup
-dontwarn org.openxmlformats.schemas.officeDocument.x2006.math.CTSSup
-dontwarn org.openxmlformats.schemas.officeDocument.x2006.math.CTShp
-dontwarn org.openxmlformats.schemas.officeDocument.x2006.math.CTSpacingRule
-dontwarn org.openxmlformats.schemas.officeDocument.x2006.math.CTUnSignedInteger
-dontwarn org.openxmlformats.schemas.officeDocument.x2006.math.CTYAlign
-dontwarn org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STConformanceClass
-dontwarn org.openxmlformats.schemas.presentationml.x2006.main.CTBuildList
-dontwarn org.openxmlformats.schemas.presentationml.x2006.main.CTControlList
-dontwarn org.openxmlformats.schemas.presentationml.x2006.main.CTCustomShowList
-dontwarn org.openxmlformats.schemas.presentationml.x2006.main.CTCustomerData
-dontwarn org.openxmlformats.schemas.presentationml.x2006.main.CTEmpty
-dontwarn org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionListModify
-dontwarn org.openxmlformats.schemas.presentationml.x2006.main.CTKinsoku
-dontwarn org.openxmlformats.schemas.presentationml.x2006.main.CTModifyVerifier
-dontwarn org.openxmlformats.schemas.presentationml.x2006.main.CTOleObjectEmbed
-dontwarn org.openxmlformats.schemas.presentationml.x2006.main.CTOleObjectLink
-dontwarn org.openxmlformats.schemas.presentationml.x2006.main.CTPhotoAlbum
-dontwarn org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayoutIdList
-dontwarn org.openxmlformats.schemas.presentationml.x2006.main.CTSlideTransition
-dontwarn org.openxmlformats.schemas.presentationml.x2006.main.CTSmartTags
-dontwarn org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateBehavior
-dontwarn org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateColorBehavior
-dontwarn org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateEffectBehavior
-dontwarn org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateMotionBehavior
-dontwarn org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateRotationBehavior
-dontwarn org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateScaleBehavior
-dontwarn org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommandBehavior
-dontwarn org.openxmlformats.schemas.presentationml.x2006.main.CTTLIterateData
-dontwarn org.openxmlformats.schemas.presentationml.x2006.main.CTTLMediaNodeAudio
-dontwarn org.openxmlformats.schemas.presentationml.x2006.main.CTTLOleChartTargetElement
-dontwarn org.openxmlformats.schemas.presentationml.x2006.main.CTTLSetBehavior
-dontwarn org.openxmlformats.schemas.presentationml.x2006.main.CTTLSubShapeId
-dontwarn org.openxmlformats.schemas.presentationml.x2006.main.CTTLTextTargetElement
-dontwarn org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeNodeExclusive
-dontwarn org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeNodeSequence
-dontwarn org.openxmlformats.schemas.presentationml.x2006.main.CTTLTriggerRuntimeNode
-dontwarn org.openxmlformats.schemas.presentationml.x2006.main.CTTLTriggerTimeNodeID
-dontwarn org.openxmlformats.schemas.presentationml.x2006.main.STDirection
-dontwarn org.openxmlformats.schemas.presentationml.x2006.main.STIndex
-dontwarn org.openxmlformats.schemas.presentationml.x2006.main.STTLTimeNodeID
-dontwarn org.openxmlformats.schemas.presentationml.x2006.main.STTLTimeNodeMasterRelation
-dontwarn org.openxmlformats.schemas.presentationml.x2006.main.STTLTimeNodePresetClassType
-dontwarn org.openxmlformats.schemas.presentationml.x2006.main.STTLTimeNodeSyncType
-dontwarn org.openxmlformats.schemas.presentationml.x2006.main.STTLTriggerEvent
-dontwarn org.openxmlformats.schemas.schemaLibrary.x2006.main.CTSchemaLibrary
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTAutoSortScope
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBoolean
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCacheHierarchies
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalculatedItems
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalculatedMembers
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellStyles
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartFormats
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheetPr
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheetProtection
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheetViews
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColHierarchiesUsage
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColItems
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCommentPr
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTConditionalFormats
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTConsolidation
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTControlPr
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCsPageSetup
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomChartsheetViews
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBinding
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataRefs
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDateTime
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDdeLink
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDimensions
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDrawingHF
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTError
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFieldGroup
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilterColumn
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFormats
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFunctionGroup
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTGradientFill
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMRUColors
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMeasureDimensionMaps
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMeasureGroups
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMissing
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTNumber
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTObjectPr
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOleLink
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPCDKPIs
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotFilters
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotHierarchies
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotSelection
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRecord
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRowHierarchiesUsage
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRowItems
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTScenarios
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSmartTagPr
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSmartTagTypes
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSmartTags
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTString
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTupleCache
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishItems
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishObjects
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.CTX
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.STComments
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.STDataValidationImeMode
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.STFieldSortType
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.STOleUpdate
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.STPhoneticAlignment
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.STShowDataAs
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.STSortBy
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.STSortMethod
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.STTableType
-dontwarn org.openxmlformats.schemas.spreadsheetml.x2006.main.STTargetScreenSize
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAltChunkPr
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCaptions
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCellMergeTrackChange
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCharacterSpacing
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColorSchemeMapping
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColumn
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCompat
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTControl
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCustomXmlBlock
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCustomXmlCell
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCustomXmlRow
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumberOrPrecent
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocRsids
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocType
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocVars
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEastAsianLayout
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEdnDocProps
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEdnPos
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFDDList
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFHelpText
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFStatusText
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFTextType
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnDocProps
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHeaders
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTKinsoku
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLevelSuffix
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLineNumber
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLongHexNumber
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvlLegacy
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMacroName
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMailMerge
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMathCtrlDel
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMathCtrlIns
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMultiLevelType
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumPicBullet
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObjectEmbed
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObjectLink
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrChange
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTProof
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTReadingModeInkLockDown
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSaveThroughXslt
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtRow
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPrChange
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShapeDefaults
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSmartTagType
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStylePaneFilter
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyleSort
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblGridChange
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrChange
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExChange
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblStylePr
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrChange
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTextEffect
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTextboxTightWrap
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrChange
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangeNumbering
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangesView
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTwipsMeasure
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTUnsignedDecimalNumber
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTView
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTWriteProtection
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.CTWritingStyle
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.STChapterSep
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.STDropCap
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.STPTabAlignment
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.STPTabLeader
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.STPTabRelativeTo
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.STPageBorderZOrder
-dontwarn org.openxmlformats.schemas.wordprocessingml.x2006.main.STZoom
-dontwarn org.osgi.framework.Bundle
-dontwarn org.osgi.framework.BundleContext
-dontwarn org.osgi.framework.FrameworkUtil
-dontwarn org.osgi.framework.ServiceReference
-dontwarn org.slf4j.impl.StaticLoggerBinder
-dontwarn org.slf4j.impl.StaticMDCBinder
-dontwarn org.tukaani.xz.ARMOptions
-dontwarn org.tukaani.xz.ARMThumbOptions
-dontwarn org.tukaani.xz.FilterOptions
-dontwarn org.tukaani.xz.IA64Options
-dontwarn org.tukaani.xz.LZMA2Options
-dontwarn org.tukaani.xz.PowerPCOptions
-dontwarn org.tukaani.xz.SPARCOptions
-dontwarn org.tukaani.xz.X86Options
-dontwarn org.w3.x2000.x09.xmldsig.KeyInfoType
-dontwarn org.w3.x2000.x09.xmldsig.SignatureMethodType
-dontwarn org.w3.x2000.x09.xmldsig.TransformsType

-dontwarn java.awt.Color
-dontwarn java.awt.Dimension
-dontwarn java.awt.Rectangle
-dontwarn java.awt.color.ColorSpace
-dontwarn java.awt.geom.AffineTransform
-dontwarn java.awt.geom.Dimension2D
-dontwarn java.awt.geom.Path2D
-dontwarn java.awt.geom.PathIterator
-dontwarn java.awt.geom.Point2D
-dontwarn java.awt.geom.Rectangle2D$Double
-dontwarn java.awt.geom.Rectangle2D
-dontwarn java.awt.image.BufferedImage
-dontwarn java.awt.image.ColorModel
-dontwarn java.awt.image.ComponentColorModel
-dontwarn java.awt.image.DirectColorModel
-dontwarn java.awt.image.IndexColorModel
-dontwarn java.awt.image.PackedColorModel

-dontwarn okio.**
-dontwarn okhttp3.**
-dontwarn retrofit2.**


-keepattributes Signature, InnerClasses, EnclosingMethod

# Retrofit does reflection on method and parameter annotations.
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations

# Keep annotation default values (e.g., retrofit2.http.Field.encoded).
-keepattributes AnnotationDefault

# Retain service method parameters when optimizing.
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

# Ignore annotation used for build tooling.
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

# Ignore JSR 305 annotations for embedding nullability information.
-dontwarn javax.annotation.**

# Guarded by a NoClassDefFoundError try/catch and only used when on the classpath.
-dontwarn kotlin.Unit

# Top-level functions that can only be used by Kotlin.
-dontwarn retrofit2.KotlinExtensions
-dontwarn retrofit2.KotlinExtensions$*

# With R8 full mode, it sees no subtypes of Retrofit interfaces since they are created with a Proxy
# and replaces all potential values with null. Explicitly keeping the interfaces prevents this.
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface <1>

# Keep inherited services.
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface * extends <1>

# With R8 full mode generic signatures are stripped for classes that are not
# kept. Suspend functions are wrapped in continuations where the type argument
# is used.
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation

# R8 full mode strips generic signatures from return types if not kept.
-if interface * { @retrofit2.http.* public *** *(...); }
-keep,allowoptimization,allowshrinking,allowobfuscation class <3>

# With R8 full mode generic signatures are stripped for classes that are not kept.
-keep,allowobfuscation,allowshrinking class retrofit2.Response

-keep class com.jmp.data.**.model.** { *; }
