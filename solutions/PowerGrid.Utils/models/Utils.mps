<?xml version="1.0" encoding="UTF-8"?>
<model ref="r:b4263613-04e2-445e-bee6-d68be65f39c8(PowerGrid.Runtime.Utils)">
  <persistence version="9" />
  <languages>
    <use id="f3061a53-9226-4cc5-a443-f952ceaf5816" name="jetbrains.mps.baseLanguage" version="5" />
    <use id="f2801650-65d5-424e-bb1b-463a8781b786" name="jetbrains.mps.baseLanguage.javadoc" version="2" />
  </languages>
  <imports>
    <import index="8t2c" ref="06938a7a-d23b-45e2-95a4-0d0c6d8a569c/java:nz.ac.victoria.swen424(ExternalCode/)" />
    <import index="66tq" ref="r:b4263613-04e2-445e-bee6-d68be65f39c8(PowerGrid.Runtime.Utils)" />
  </imports>
  <registry>
    <language id="f3061a53-9226-4cc5-a443-f952ceaf5816" name="jetbrains.mps.baseLanguage">
      <concept id="1215693861676" name="jetbrains.mps.baseLanguage.structure.BaseAssignmentExpression" flags="nn" index="d038R">
        <child id="1068498886297" name="rValue" index="37vLTx" />
        <child id="1068498886295" name="lValue" index="37vLTJ" />
      </concept>
      <concept id="1070534370425" name="jetbrains.mps.baseLanguage.structure.IntegerType" flags="in" index="10Oyi0" />
      <concept id="1068390468200" name="jetbrains.mps.baseLanguage.structure.FieldDeclaration" flags="ig" index="312cEg">
        <property id="8606350594693632173" name="isTransient" index="eg7rD" />
        <property id="1240249534625" name="isVolatile" index="34CwA1" />
      </concept>
      <concept id="1068390468198" name="jetbrains.mps.baseLanguage.structure.ClassConcept" flags="ig" index="312cEu" />
      <concept id="1068431474542" name="jetbrains.mps.baseLanguage.structure.VariableDeclaration" flags="ng" index="33uBYm">
        <property id="1176718929932" name="isFinal" index="3TUv4t" />
      </concept>
      <concept id="1068498886296" name="jetbrains.mps.baseLanguage.structure.VariableReference" flags="nn" index="37vLTw">
        <reference id="1068581517664" name="variableDeclaration" index="3cqZAo" />
      </concept>
      <concept id="1068498886292" name="jetbrains.mps.baseLanguage.structure.ParameterDeclaration" flags="ir" index="37vLTG" />
      <concept id="1068498886294" name="jetbrains.mps.baseLanguage.structure.AssignmentExpression" flags="nn" index="37vLTI" />
      <concept id="1225271177708" name="jetbrains.mps.baseLanguage.structure.StringType" flags="in" index="17QB3L" />
      <concept id="4972933694980447171" name="jetbrains.mps.baseLanguage.structure.BaseVariableDeclaration" flags="ng" index="19Szcq">
        <child id="5680397130376446158" name="type" index="1tU5fm" />
      </concept>
      <concept id="1068580123132" name="jetbrains.mps.baseLanguage.structure.BaseMethodDeclaration" flags="ng" index="3clF44">
        <child id="1068580123133" name="returnType" index="3clF45" />
        <child id="1068580123134" name="parameter" index="3clF46" />
        <child id="1068580123135" name="body" index="3clF47" />
      </concept>
      <concept id="1068580123155" name="jetbrains.mps.baseLanguage.structure.ExpressionStatement" flags="nn" index="3clFbF">
        <child id="1068580123156" name="expression" index="3clFbG" />
      </concept>
      <concept id="1068580123136" name="jetbrains.mps.baseLanguage.structure.StatementList" flags="sn" stub="5293379017992965193" index="3clFbS">
        <child id="1068581517665" name="statement" index="3cqZAp" />
      </concept>
      <concept id="1068580123140" name="jetbrains.mps.baseLanguage.structure.ConstructorDeclaration" flags="ig" index="3clFbW" />
      <concept id="1068581517677" name="jetbrains.mps.baseLanguage.structure.VoidType" flags="in" index="3cqZAl" />
      <concept id="1107461130800" name="jetbrains.mps.baseLanguage.structure.Classifier" flags="ng" index="3pOWGL">
        <child id="5375687026011219971" name="member" index="jymVt" unordered="true" />
      </concept>
      <concept id="1178549954367" name="jetbrains.mps.baseLanguage.structure.IVisible" flags="ng" index="1B3ioH">
        <child id="1178549979242" name="visibility" index="1B3o_S" />
      </concept>
      <concept id="1146644602865" name="jetbrains.mps.baseLanguage.structure.PublicVisibility" flags="nn" index="3Tm1VV" />
      <concept id="1146644623116" name="jetbrains.mps.baseLanguage.structure.PrivateVisibility" flags="nn" index="3Tm6S6" />
    </language>
    <language id="ceab5195-25ea-4f22-9b92-103b95ca8c0c" name="jetbrains.mps.lang.core">
      <concept id="1169194658468" name="jetbrains.mps.lang.core.structure.INamedConcept" flags="ng" index="TrEIO">
        <property id="1169194664001" name="name" index="TrG5h" />
      </concept>
    </language>
  </registry>
  <node concept="312cEu" id="Kb51drnN1a">
    <property role="TrG5h" value="ElTransformer" />
    <node concept="312cEg" id="3V6Jp6s3FmL" role="jymVt">
      <property role="34CwA1" value="false" />
      <property role="eg7rD" value="false" />
      <property role="TrG5h" value="_name" />
      <property role="3TUv4t" value="false" />
      <node concept="3Tm6S6" id="3V6Jp6s3Fif" role="1B3o_S" />
      <node concept="17QB3L" id="3V6Jp6s3Fmt" role="1tU5fm" />
    </node>
    <node concept="312cEg" id="Kb51drnN8T" role="jymVt">
      <property role="34CwA1" value="false" />
      <property role="eg7rD" value="false" />
      <property role="TrG5h" value="_maxcapacity" />
      <property role="3TUv4t" value="false" />
      <node concept="3Tm6S6" id="Kb51drnN8_" role="1B3o_S" />
      <node concept="10Oyi0" id="Kb51drnN8O" role="1tU5fm" />
    </node>
    <node concept="312cEg" id="6qPpZy2sUZE" role="jymVt">
      <property role="34CwA1" value="false" />
      <property role="eg7rD" value="false" />
      <property role="TrG5h" value="_efficiency" />
      <property role="3TUv4t" value="false" />
      <node concept="3Tm6S6" id="6qPpZy2sUSO" role="1B3o_S" />
      <node concept="10Oyi0" id="6qPpZy2sUZ_" role="1tU5fm" />
    </node>
    <node concept="3clFbW" id="Kb51drnN7D" role="jymVt">
      <node concept="3cqZAl" id="Kb51drnN7E" role="3clF45" />
      <node concept="3clFbS" id="Kb51drnN7G" role="3clF47">
        <node concept="3clFbF" id="6qPpZy2szh9" role="3cqZAp">
          <node concept="37vLTI" id="6qPpZy2szTs" role="3clFbG">
            <node concept="37vLTw" id="6qPpZy2szYS" role="37vLTx">
              <ref role="3cqZAo" node="Kb51drnN7Y" resolve="name" />
            </node>
            <node concept="37vLTw" id="6qPpZy2szh7" role="37vLTJ">
              <ref role="3cqZAo" node="3V6Jp6s3FmL" resolve="_name" />
            </node>
          </node>
        </node>
        <node concept="3clFbF" id="Kb51drnN9m" role="3cqZAp">
          <node concept="37vLTI" id="Kb51drnOBL" role="3clFbG">
            <node concept="37vLTw" id="6qPpZy2syPr" role="37vLTx">
              <ref role="3cqZAo" node="6qPpZy2swCL" resolve="maxcapacity" />
            </node>
            <node concept="37vLTw" id="Kb51drnN9l" role="37vLTJ">
              <ref role="3cqZAo" node="Kb51drnN8T" resolve="_maxcapacity" />
            </node>
          </node>
        </node>
        <node concept="3clFbF" id="6qPpZy2sVsN" role="3cqZAp">
          <node concept="37vLTI" id="6qPpZy2sYz3" role="3clFbG">
            <node concept="37vLTw" id="6qPpZy2sYL2" role="37vLTx">
              <ref role="3cqZAo" node="6qPpZy2sV6t" resolve="efficiency" />
            </node>
            <node concept="37vLTw" id="6qPpZy2sVsL" role="37vLTJ">
              <ref role="3cqZAo" node="6qPpZy2sUZE" resolve="_efficiency" />
            </node>
          </node>
        </node>
      </node>
      <node concept="3Tm1VV" id="Kb51drnN7u" role="1B3o_S" />
      <node concept="37vLTG" id="Kb51drnN7Y" role="3clF46">
        <property role="TrG5h" value="name" />
        <node concept="17QB3L" id="6qPpZy2svAa" role="1tU5fm" />
      </node>
      <node concept="37vLTG" id="6qPpZy2swCL" role="3clF46">
        <property role="TrG5h" value="maxcapacity" />
        <node concept="10Oyi0" id="6qPpZy2swMH" role="1tU5fm" />
      </node>
      <node concept="37vLTG" id="6qPpZy2sV6t" role="3clF46">
        <property role="TrG5h" value="efficiency" />
        <node concept="10Oyi0" id="6qPpZy2sVcW" role="1tU5fm" />
      </node>
    </node>
    <node concept="3Tm1VV" id="Kb51drnN1b" role="1B3o_S" />
  </node>
</model>

